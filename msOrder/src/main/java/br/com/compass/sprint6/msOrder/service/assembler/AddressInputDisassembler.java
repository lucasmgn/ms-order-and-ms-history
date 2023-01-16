package br.com.compass.sprint6.msOrder.service.assembler;

import br.com.compass.sprint6.msOrder.entities.Address;
import br.com.compass.sprint6.msOrder.entities.Item;
import br.com.compass.sprint6.msOrder.service.dto.request.AddressResumeRequestDTO;
import br.com.compass.sprint6.msOrder.service.dto.request.ItemRequestDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressInputDisassembler {

    private final ModelMapper modelMapper;

    public Address toDomainObject(AddressResumeRequestDTO addressRequestDTO) {
        return modelMapper.map(addressRequestDTO, Address.class);
    }

    public void copyToDomainObject(ItemRequestDTO itemRequestDTO, Item item){
        modelMapper.map(itemRequestDTO, item);
    }
}
