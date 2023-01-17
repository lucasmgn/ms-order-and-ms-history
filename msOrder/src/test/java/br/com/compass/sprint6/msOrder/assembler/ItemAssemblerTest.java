package br.com.compass.sprint6.msOrder.assembler;

import br.com.compass.sprint6.msOrder.domain.model.Item;
import br.com.compass.sprint6.msOrder.application.service.assembler.ItemDTOAssembler;
import br.com.compass.sprint6.msOrder.domain.dto.response.ItemResponseDTO;
import br.com.compass.sprint6.msOrder.domain.dto.response.ItemResumeResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ItemAssemblerTest {

    @InjectMocks
    private ItemDTOAssembler service;


    @Spy
    private ModelMapper mapper;

    @Test
    void toModelSucess(){
        Item item = getItem();
        ItemResponseDTO itemResponseDTO = service.toModel(item);
        Assertions.assertEquals(item.getDescription(), itemResponseDTO.getDescription());
    }

    public Item getItem() {
        Item i = new Item();
        i.setName("Test");
        i.setCreation(LocalDate.of(2002, 01, 02));
        i.setExpiration(LocalDate.of(2002, 01, 03));
        i.setPrice(new BigDecimal("12"));
        i.setDescription("test");
        return i;
    }

    @Test
    void toColletionModelSucess(){
        List<Item> item = List.of(getItem());
        List<ItemResponseDTO> itemResponseDTOS = service.toCollectionModel(item);
        Assertions.assertEquals(item.get(0).getName(), itemResponseDTOS.get(0).getName());
    }

    @Test
    void toResumeModel(){
        Item item = getItem();
        ItemResumeResponseDTO itemResumeResponseDTO = service.toResumeModel(item);
        Assertions.assertEquals(item.getName(), itemResumeResponseDTO.getName());
    }
}
