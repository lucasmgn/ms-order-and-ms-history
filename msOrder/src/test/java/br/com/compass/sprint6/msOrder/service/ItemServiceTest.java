package br.com.compass.sprint6.msOrder.service;

import br.com.compass.sprint6.msOrder.application.service.ItemService;
import br.com.compass.sprint6.msOrder.domain.model.Item;
import br.com.compass.sprint6.msOrder.framework.exceptions.response.InvalidDateException;
import br.com.compass.sprint6.msOrder.framework.exceptions.response.ItemNotFoundException;
import br.com.compass.sprint6.msOrder.framework.adapter.out.database.ItemRepository;
import br.com.compass.sprint6.msOrder.application.service.mapper.ItemDTOAssembler;
import br.com.compass.sprint6.msOrder.domain.dto.request.OrderRequestDTO;
import br.com.compass.sprint6.msOrder.domain.dto.response.ItemResumeResponseDTO;
import br.com.compass.sprint6.msOrder.utils.CreateObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
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
    @Spy
    private ObjectMapper objectMapper;

    @Test
    void shouldFindItemById_NotFound() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> service.fetchOrFail(ID));
    }

    @Test
    void shouldUpdateItem_success() {
        Item item = createObject.item();
        item.setId(ID);
        ItemResumeResponseDTO itemResumeResponseDTO = createObject.getItemResumeResponseDTO();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(item));
        Mockito.when(repository.save(any())).thenReturn(item);
        Mockito.when(assembler.toResumeModel(any())).thenReturn(itemResumeResponseDTO);

        ItemResumeResponseDTO responseDTO = service.updateItem(ID);

        assertEquals(itemResumeResponseDTO.getPrice(), responseDTO.getPrice());
        verify(repository).save(any());
    }

    @Test
    void shouldVerifyDate_error() {
        OrderRequestDTO oderRequestDTO = createObject.getOderRequestDTO();
        oderRequestDTO.getItems().get(0).setCreation(LocalDate.of(2004, 12, 01));
        assertThrows(InvalidDateException.class, () -> service.verifyDate(oderRequestDTO));
    }

    @Test
    void shouldMerge() {
        Map<String, Object> sourceData = new HashMap<>();
        Item item = createObject.item();
        sourceData.put("creation", "01-01-2023");
        service.merge(sourceData, item);
        assertNotEquals(sourceData.get("creation"), item.getCreation());
    }

    @Test
    void shouldGetTotal() {
        OrderRequestDTO oderRequestDTO = createObject.getOderRequestDTO();

        service.getTotal(oderRequestDTO);

        assertEquals(new BigDecimal("15"), oderRequestDTO.getTotal());
    }
}
