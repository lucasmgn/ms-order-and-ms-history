package br.com.compass.sprint6.msOrder.service;

import br.com.compass.sprint6.msOrder.entities.Item;
import br.com.compass.sprint6.msOrder.exceptions.response.ItemNotFoundException;
import br.com.compass.sprint6.msOrder.exceptions.response.OrderNotFoundException;
import br.com.compass.sprint6.msOrder.repository.ItemRepository;
import br.com.compass.sprint6.msOrder.service.assembler.ItemDTOAssembler;
import br.com.compass.sprint6.msOrder.service.dto.request.ItemRequestDTO;
import br.com.compass.sprint6.msOrder.service.dto.response.ItemResponseDTO;
import br.com.compass.sprint6.msOrder.service.dto.response.ItemResumeResponseDTO;
import br.com.compass.sprint6.msOrder.utils.CreateObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    void shouldFindItemById_NotFound() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> service.fetchOrFail(ID));
    }

    @Test
    void shouldUpdateItem_success() {
        Item item = createObject.item();
        ItemResponseDTO response = createObject. itemResponseDTO();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(item));
        Mockito.when(repository.save(any())).thenReturn(item);

        ItemResumeResponseDTO itemResponseDTO = service.updateItem(ID);
        assertEquals(response, itemResponseDTO);
        verify(repository).save(any());
    }

//    @Test
//    void shouldDeleteCompany_success() {
//        service.create(ID);
//        verify(repository).deleteById(any());
//    }
}
