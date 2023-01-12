package br.com.compass.sprint6.msOrder.service.assembler;

import br.com.compass.sprint6.msOrder.entities.Item;
import br.com.compass.sprint6.msOrder.service.dto.request.ItemRequestDTO;
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