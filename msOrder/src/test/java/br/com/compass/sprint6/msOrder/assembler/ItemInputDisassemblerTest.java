package br.com.compass.sprint6.msOrder.assembler;

import br.com.compass.sprint6.msOrder.entities.Item;
import br.com.compass.sprint6.msOrder.service.assembler.ItemInputDisassembler;
import br.com.compass.sprint6.msOrder.service.dto.request.ItemRequestDTO;
import br.com.compass.sprint6.msOrder.utils.CreateObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class ItemInputDisassemblerTest {
    @InjectMocks
    private ItemInputDisassembler service;

    @Spy
    private ModelMapper mapper;

    @InjectMocks
    private CreateObject createObject;

    @Test
    void toDomainObject_Sucess(){
        ItemRequestDTO itemRequestDTO = new ItemRequestDTO();
        itemRequestDTO.setName("test");
        Item item = service.toDomainObject(itemRequestDTO);
        Assertions.assertEquals(item.getName(), itemRequestDTO.getName());
    }

}
