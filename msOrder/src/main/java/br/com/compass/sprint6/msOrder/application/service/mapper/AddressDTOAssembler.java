package br.com.compass.sprint6.msOrder.application.service.mapper;

import br.com.compass.sprint6.msOrder.domain.dto.request.AddressResumeRequestDTO;
import br.com.compass.sprint6.msOrder.domain.dto.response.AddressResponseDTO;
import br.com.compass.sprint6.msOrder.domain.model.Address;
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
