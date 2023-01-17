package br.com.compass.sprint6.msOrder.service;

import br.com.compass.sprint6.msOrder.entities.Address;
import br.com.compass.sprint6.msOrder.repository.AddressRepository;
import br.com.compass.sprint6.msOrder.service.assembler.AddressDTOAssembler;
import br.com.compass.sprint6.msOrder.service.assembler.AddressInputDisassembler;
import br.com.compass.sprint6.msOrder.service.dto.request.AddressResumeRequestDTO;
import br.com.compass.sprint6.msOrder.service.dto.request.OrderRequestDTO;
import br.com.compass.sprint6.msOrder.service.dto.response.AddressResponseViaCepDTO;
import br.com.compass.sprint6.msOrder.utils.CreateObject;
import br.com.compass.sprint6.msOrder.viacep.ViaCepClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    private AddressRepository repository;
    @Mock
    private AddressDTOAssembler assembler;
    @Mock
    private AddressInputDisassembler disassembler;
    @Mock
    private ViaCepClient client;
    @InjectMocks
    private CreateObject createObject;
    @InjectMocks
    private AddressService service;

    @Test
    void should_verifyAddressExistAddress(){
        OrderRequestDTO request = createObject.getOderRequestDTO();
        AddressResponseViaCepDTO addressResponseViaCepDTO = new AddressResponseViaCepDTO();
        Address address = new Address();
        address.setCep("40430390");
        AddressResumeRequestDTO addressResumeRequestDTO = new AddressResumeRequestDTO();

        Mockito.when(client.find(request.getAddress().getCep())).thenReturn(addressResponseViaCepDTO);
        Mockito.when(repository.findByCepAndNumber(any(),any())).thenReturn(address);
        Mockito.when(assembler.toResumeModel(any())).thenReturn(addressResumeRequestDTO);

        Address address1 = service.verifyAddress(request);

        assertEquals("40430390", address1.getCep());
    }

    @Test
    void should_verifyAddressNoExistsAddress(){
        OrderRequestDTO request = createObject.getOderRequestDTO();
        AddressResponseViaCepDTO addressResponseViaCepDTO = new AddressResponseViaCepDTO();
        Address address = new Address();;

        Mockito.when(client.find(request.getAddress().getCep())).thenReturn(addressResponseViaCepDTO);
        Mockito.when(repository.findByCepAndNumber(any(),any())).thenReturn(null);
        Mockito.when(disassembler.toDomainObject(request.getAddress())).thenReturn(address);

       service.verifyAddress(request);

        verify(repository).save(any());
    }
}
