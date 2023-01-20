package br.com.compass.sprint6.mshistory.mshistory.service;

import br.com.compass.sprint6.mshistory.mshistory.application.service.HistoryService;
import br.com.compass.sprint6.mshistory.mshistory.application.service.assembler.HistoryDTOAssembler;
import br.com.compass.sprint6.mshistory.mshistory.application.service.assembler.HistoryInputDisassembler;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
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

        History history = new History();
        history.setTotal(new BigDecimal("1"));

        Mockito.when(disassembler.toDomainObject(any())).thenReturn(history);
        Mockito.when(repository.save(any())).thenReturn(history);
        Mockito.when(assembler.toModel(any())).thenReturn(response);

        HistoryResponseDTO historyResponseDTO = service.create(request);
        assertEquals(response.getTotal(), historyResponseDTO.getTotal());
        verify(repository).save(any());
    }
}
