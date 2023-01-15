package br.com.compass.sprint6.msOrder.assembler;

import br.com.compass.sprint6.msOrder.entities.Item;
import br.com.compass.sprint6.msOrder.entities.Order;
import br.com.compass.sprint6.msOrder.service.assembler.ItemDTOAssembler;
import br.com.compass.sprint6.msOrder.service.assembler.OrderDTOAssembler;
import br.com.compass.sprint6.msOrder.service.dto.response.ItemResponseDTO;
import br.com.compass.sprint6.msOrder.service.dto.response.ItemResumeResponseDTO;
import br.com.compass.sprint6.msOrder.service.dto.response.OrderResponseDTO;
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

//    @Test
//    void toModelSucess(){
//        Item item = getItem();
//        ItemResponseDTO itemResponseDTO = service.toModel(item);
//        Assertions.assertEquals(item.getName(), itemResponseDTO.getName());
//    }
//
//    @Test
//    void toColletionModelSucess(){
//        List<Item> item = List.of(getItem());
//        List<ItemResponseDTO> itemResponseDTOS = service.toCollectionModel(item);
//        Assertions.assertEquals(item.get(0).getName(), itemResponseDTOS.get(0).getName());
//    }
//
//    @Test
//    void toResumeModel(){
//        Item item = getItem();
//        ItemResumeResponseDTO itemResumeResponseDTO = service.toResumeModel(item);
//        Assertions.assertEquals(item.getName(), itemResumeResponseDTO.getName());
//    }
}
