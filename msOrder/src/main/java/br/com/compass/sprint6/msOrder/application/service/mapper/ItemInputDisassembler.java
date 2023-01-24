package br.com.compass.sprint6.msOrder.application.service.mapper;

import br.com.compass.sprint6.msOrder.domain.dto.request.ItemRequestDTO;
import br.com.compass.sprint6.msOrder.domain.model.Item;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemInputDisassembler {

    private final ModelMapper modelMapper;

    public Item toDomainObject(ItemRequestDTO itemRequestDTO) {
        return modelMapper.map(itemRequestDTO, Item.class);
    }

    public void copyToDomainObject(ItemRequestDTO itemRequestDTO, Item item){
        modelMapper.map(itemRequestDTO, item);
    }
}
