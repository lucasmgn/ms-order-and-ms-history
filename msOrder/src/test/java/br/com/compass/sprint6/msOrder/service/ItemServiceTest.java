package br.com.compass.sprint6.msOrder.service;

import br.com.compass.sprint6.msOrder.entities.Item;
import br.com.compass.sprint6.msOrder.exceptions.response.InvalidDateException;
import br.com.compass.sprint6.msOrder.exceptions.response.ItemNotFoundException;
import br.com.compass.sprint6.msOrder.repository.ItemRepository;
import br.com.compass.sprint6.msOrder.service.assembler.ItemDTOAssembler;
import br.com.compass.sprint6.msOrder.service.dto.request.OrderRequestDTO;
import br.com.compass.sprint6.msOrder.service.dto.response.ItemResponseDTO;
import br.com.compass.sprint6.msOrder.service.dto.response.ItemResumeResponseDTO;
import br.com.compass.sprint6.msOrder.utils.CreateObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    public static final Long ID = 1L;

    @Mock
    private ItemDTOAssembler assembler;
    @Mock
    private ItemRepository repository;
    @InjectMocks
    private ItemService service;
    @InjectMocks
    private CreateObject createObject;

    @Mock
    private ObjectMapper objectMapper;

//    @Mock
    private Field field;

    @Test
    void shouldFindItemById_NotFound() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> service.fetchOrFail(ID));
    }

    @Test
    void shouldUpdateItem_success() {
        Item item = createObject.item();
        ItemResponseDTO response = createObject.itemResponseDTO();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(item));
        Mockito.when(repository.save(any())).thenReturn(item);

        ItemResumeResponseDTO itemResponseDTO = service.updateItem(ID);
        assertEquals(response, itemResponseDTO);
        verify(repository).save(any());
    }

    @Test
    void shouldVerifyDate_error() {
        OrderRequestDTO oderRequestDTO = createObject.getOderRequestDTO();
        oderRequestDTO.getItems().get(0).setCreation(LocalDate.of(2004, 12, 01));
        assertThrows(InvalidDateException.class, () -> service.verifyDate(oderRequestDTO));
    }

    //Tirar dúvida
//    @Test
//    void shouldMerge() {
//        Map<String, Object> sourceData = new HashMap<>();
//        Object obj = new Object();
//        Item item = createObject.item();
//        sourceData.put("name", item);
//
//        Mockito.when(objectMapper.convertValue(sourceData, Item.class)).thenReturn(item);
//        Mockito.when(ReflectionUtils.findField(Item.class, "name")).thenReturn(field);
//        Mockito.when(ReflectionUtils.getField(any(), any())).thenReturn(obj);
////        Mockito.when(ReflectionUtils.setField(field, item , obj));
//        service.merge(sourceData, item);
//
//        assertNotEquals(item.getName(), sourceData.values());
//    }

}
