package br.com.compass.sprint6.mshistory.mshistory.service;

import br.com.compass.sprint6.mshistory.mshistory.application.service.HistoryService;
import br.com.compass.sprint6.mshistory.mshistory.application.service.mapper.HistoryDTOAssembler;
import br.com.compass.sprint6.mshistory.mshistory.application.service.mapper.HistoryInputDisassembler;
import br.com.compass.sprint6.mshistory.mshistory.domain.dto.request.HistoryRequestDTO;
import br.com.compass.sprint6.mshistory.mshistory.domain.dto.response.HistoryResponseDTO;
import br.com.compass.sprint6.mshistory.mshistory.domain.model.History;
import br.com.compass.sprint6.mshistory.mshistory.framework.adapter.out.database.HistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HistoryServiceTest {

    public static final Long ID = 1L;
    @InjectMocks
    private HistoryService service;
    @Mock
    private HistoryRepository repository;
    @Mock
    private HistoryDTOAssembler assembler;
    @Mock
    private HistoryInputDisassembler disassembler;


    @Test
    void shouldVerify_FindAll() {
        List<History> history = List.of(new History());
        List<HistoryResponseDTO> response = List.of(new HistoryResponseDTO());

        Mockito.when(repository.findAll()).thenReturn(history);
        Mockito.when(assembler.toCollectionModel(history)).thenReturn(response);

        List<HistoryResponseDTO> all = service.findAll();

        assertEquals(history.get(0).getTotal(), all.get(0).getTotal());
    }

    @Test
    void shouldCreateHistory_success() {
        HistoryRequestDTO request = new HistoryRequestDTO();

        HistoryResponseDTO response = new HistoryResponseDTO();
        response.setTotal(new BigDecimal("1"));
        response.setIdOrder(1L);
        response.setDate(LocalDate.now());
        response.setCod("ALALALA");

        History history = new History();
        history.setTotal(new BigDecimal("1"));

        Mockito.when(disassembler.toDomainObject(any())).thenReturn(history);
        Mockito.when(repository.save(any())).thenReturn(history);
        Mockito.when(assembler.toModel(any())).thenReturn(response);

        HistoryResponseDTO historyResponseDTO = service.create(request);
        assertEquals(response.getTotal(), historyResponseDTO.getTotal());
        assertEquals(response.getCod(), historyResponseDTO.getCod());
        assertEquals(response.getDate(), historyResponseDTO.getDate());
        assertEquals(response.getIdOrder(), historyResponseDTO.getIdOrder());
        verify(repository).save(any());
    }
}
