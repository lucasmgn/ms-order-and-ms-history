package br.com.compass.sprint6.msOrder.service;
import br.com.compass.sprint6.msOrder.service.assembler.OrderDTOAssembler;
import br.com.compass.sprint6.msOrder.service.assembler.OrderInputDisassembler;
import br.com.compass.sprint6.msOrder.service.dto.request.ItemRequestDTO;
import br.com.compass.sprint6.msOrder.service.dto.response.OrderResponseDTO;
import br.com.compass.sprint6.msOrder.entities.Order;
import br.com.compass.sprint6.msOrder.exceptions.response.OrderNotFoundException;
import br.com.compass.sprint6.msOrder.repository.AddressRepository;
import br.com.compass.sprint6.msOrder.repository.ItemRepository;
import br.com.compass.sprint6.msOrder.repository.OrderRepository;
import br.com.compass.sprint6.msOrder.service.dto.request.OrderRequestDTO;
import br.com.compass.sprint6.msOrder.service.dto.response.AddressResponseViaCepDTO;
import br.com.compass.sprint6.msOrder.viacep.ViaCepClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final OrderDTOAssembler assembler;
    private final OrderInputDisassembler disassembler;
    private final AddressRepository addressRepository;
    private final ItemRepository itemRepository;
    private final ViaCepClient client;

    public List<OrderResponseDTO> findAll(Pageable pageable) {
        log.info("Chamando método findAll - Service Order");
        Page<Order> pageOrders = repository.findAll(pageable);
        return assembler.toCollectionModel(pageOrders.getContent());
    }

    public OrderResponseDTO findBy(Long id) {
        log.info("Chamando método findBy - Service Order");
        Order order = fetchOrFail(id);
        return assembler.toModel(order);
    }

    public OrderResponseDTO update(Long id, OrderRequestDTO request) {
        log.info("Chamando método update - Service Order");
        Order order = fetchOrFail(id);
        disassembler.copyToDomainObject(request, order);
        order = create(order);
        return assembler.toModel(order);
    }

    @Transactional
    public void delete(Long id){
        log.info("Chamando método delete (excluindo no repository) - Service Order");
        try{
            repository.deleteById(id);
            repository.flush();
        }catch (EmptyResultDataAccessException e) {
            throw new OrderNotFoundException();
        }
    }

    @Transactional
    public OrderResponseDTO create(OrderRequestDTO request) {
        log.info("Chamando método create - Service Order");
        String cep = request.getAddress().getCep().replaceAll("[^0-9]", "");
        request.getAddress().setCep(cep);
        AddressResponseViaCepDTO responseDTO = client.find((cep));
        toAdress(request, responseDTO);
        Order order = disassembler.toDomainObject(request);
        addressRepository.save(order.getAddress());
        itemRepository.saveAll(order.getItems());
        order = create(order);
        return assembler.toModel(order);
    }

    @Transactional
    public Order create(Order order) {
        log.info("Chamando método create (salvando no repository) - Service Order");
        return repository.save(order);
    }

    private Order fetchOrFail(Long id) {
        log.info("Chamando método fetchOrFail (ID) - Service Order");
        return repository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    private List<Order> fetchOrFail(String cpf) {
        log.info("Chamando método fetchOrFail (CPF) - Service Order");
        List<Order> byCpf = repository.findByCpf(cpf);
        if(byCpf.isEmpty()){
            throw new OrderNotFoundException();
        }
        return byCpf;
    }

    private static void toAdress(OrderRequestDTO request, AddressResponseViaCepDTO responseDTO) {
        request.getAddress().setState(responseDTO.getUf());
        request.getAddress().setNumber(request.getAddress().getNumber());
        request.getAddress().setCity(responseDTO.getLocalidade());
        request.getAddress().setNeighborhood(responseDTO.getBairro());
        request.getAddress().setStreet(responseDTO.getLogradouro());
    }

    public List<OrderResponseDTO> verify(Pageable pageable, String cpf) {
        log.info("Chamando método verify - Service Order");
        if(cpf != null){
            List<Order> order = fetchOrFail(cpf);
            return assembler.toCollectionModel(order);
        }else {
            return findAll(pageable);
        }
    }
}
