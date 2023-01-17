package br.com.compass.sprint6.msOrder.service;

import br.com.compass.sprint6.msOrder.entities.Address;
import br.com.compass.sprint6.msOrder.repository.AddressRepository;
import br.com.compass.sprint6.msOrder.service.assembler.AddressDTOAssembler;
import br.com.compass.sprint6.msOrder.service.assembler.AddressInputDisassembler;
import br.com.compass.sprint6.msOrder.service.dto.request.AddressResumeRequestDTO;
import br.com.compass.sprint6.msOrder.service.dto.request.OrderRequestDTO;
import br.com.compass.sprint6.msOrder.service.dto.response.AddressResponseViaCepDTO;
import br.com.compass.sprint6.msOrder.viacep.ViaCepClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository repository;
    private final AddressDTOAssembler assembler;
    private final AddressInputDisassembler disassembler;

    private final ViaCepClient client;

    @Transactional
    public Address create(Address address) {
        log.info("Chamando método create (salvando no repository) - Service Address");
        return repository.save(address);
    }

    public Address verifyAddress(OrderRequestDTO request) {
        log.info("Chamando método verifyAddress - Service Address");
        addressViaCep(request);
        Address byCepAndNumber = repository.findByCepAndNumber(request.getAddress().getCep(), request.getAddress().getNumber());
        if(byCepAndNumber != null){
            AddressResumeRequestDTO addressResumeRequestDTO = assembler.toResumeModel(byCepAndNumber);
            request.setAddress(addressResumeRequestDTO);
        }else{
            Address address = disassembler.toDomainObject(request.getAddress());
            return create(address);
        }
        log.info("Finalizado verifyAddress - Service Address");
        return byCepAndNumber;
    }

    public void addressViaCep(OrderRequestDTO request) {
        log.info("Chamando método addressViaCep - Service Address");
        String cep = request.getAddress().getCep().replaceAll("[^0-9]", "");
        request.getAddress().setCep(cep);
        AddressResponseViaCepDTO responseDTO = client.find((cep));
        toAdress(request,responseDTO);
    }

    public void toAdress(OrderRequestDTO request, AddressResponseViaCepDTO responseDTO) {
        log.info("Chamando método toAdress - Service Address");
        request.getAddress().setState(responseDTO.getUf());
        request.getAddress().setNumber(request.getAddress().getNumber());
        request.getAddress().setCity(responseDTO.getLocalidade());
        request.getAddress().setNeighborhood(responseDTO.getBairro());
        request.getAddress().setStreet(responseDTO.getLogradouro());
    }

}
