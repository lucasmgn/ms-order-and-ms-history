package br.com.compass.sprint6.msOrder.service.assembler;

import br.com.compass.sprint6.msOrder.entities.Address;
import br.com.compass.sprint6.msOrder.service.dto.request.AddressResumeRequestDTO;
import br.com.compass.sprint6.msOrder.service.dto.response.AddressResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressDTOAssembler {
    private final ModelMapper modelMapper;

    public AddressResponseDTO toModel(Address address){
        return modelMapper.map(address,AddressResponseDTO.class);
    }

    public AddressResumeRequestDTO toResumeModel(Address address){
        return modelMapper.map(address,AddressResumeRequestDTO.class);
    }
}
