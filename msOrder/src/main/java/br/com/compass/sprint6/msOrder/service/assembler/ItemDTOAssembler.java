package br.com.compass.sprint6.msOrder.service.assembler;

import br.com.compass.sprint6.msOrder.entities.Item;
import br.com.compass.sprint6.msOrder.service.dto.response.ItemResponseDTO;
import br.com.compass.sprint6.msOrder.service.dto.response.ItemResumeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ItemDTOAssembler {

    private final ModelMapper modelMapper;

    public ItemResponseDTO toModel(Item item){
        return modelMapper.map(item,ItemResponseDTO.class);
    }

    public ItemResumeResponseDTO toResumeModel(Item item){
        return modelMapper.map(item, ItemResumeResponseDTO.class);
    }

    public List<ItemResponseDTO> toCollectionModel(List<Item> items){
        return items.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
