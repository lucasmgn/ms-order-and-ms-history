package br.com.compass.sprint6.msOrder.application.service;

import br.com.compass.sprint6.msOrder.application.kafkaServer.TopicProducer;
import br.com.compass.sprint6.msOrder.application.service.assembler.OrderDTOAssembler;
import br.com.compass.sprint6.msOrder.application.service.assembler.OrderInputDisassembler;
import br.com.compass.sprint6.msOrder.domain.dto.request.OrderRequestDTO;
import br.com.compass.sprint6.msOrder.domain.dto.request.OrderUpdateRequest;
import br.com.compass.sprint6.msOrder.domain.dto.response.OrderResponseDTO;
import br.com.compass.sprint6.msOrder.domain.model.Address;
import br.com.compass.sprint6.msOrder.domain.model.Order;
import br.com.compass.sprint6.msOrder.framework.exceptions.response.OrderNotFoundException;
import br.com.compass.sprint6.msOrder.framework.adapter.out.database.ItemRepository;
import br.com.compass.sprint6.msOrder.framework.adapter.out.database.OrderRepository;
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
    private final AddressService addressService;
    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final TopicProducer producer;

    public List<OrderResponseDTO> findAll(Pageable pageable) {
        log.info("Chamando método findAll - Service Order");
        Page<Order> ordersPage = repository.findAll(pageable);
        return assembler.toCollectionModel(ordersPage.getContent());
    }

    public OrderResponseDTO findBy(Long id) {
        log.info("Chamando método findBy - Service Order");
        Order order = fetchOrFail(id);
        return assembler.toModel(order);
    }

    public OrderResponseDTO update(Long id, OrderUpdateRequest request) {
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
        itemService.verifyDate(request);
        itemService.getTotal(request);
        Address address = addressService.verifyAddress(request);
        Order order = disassembler.toDomainObject(request);
        order.setAddress(address);
        itemRepository.saveAll(order.getItems());
        order = create(order);
        OrderResponseDTO orderResponseDTO = assembler.toModel(order);
        producer.send(orderResponseDTO);
        return orderResponseDTO;
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

    public List<Order> fetchOrFail(String cpf) {
        log.info("Chamando método fetchOrFail (CPF) - Service Order");
        List<Order> byCpf = repository.findByCpf(cpf);
        if(byCpf.isEmpty()){
            throw new OrderNotFoundException();
        }
        return byCpf;
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
