package br.com.compass.sprint6.mshistory.mshistory.service;

import br.com.compass.sprint6.mshistory.mshistory.application.service.HistoryService;
import br.com.compass.sprint6.mshistory.mshistory.application.service.mapper.HistoryDTOAssembler;
import br.com.compass.sprint6.mshistory.mshistory.application.service.mapper.HistoryInputDisassembler;
import br.com.compass.sprint6.mshistory.mshistory.domain.dto.request.HistoryRequestDTO;
import br.com.compass.sprint6.mshistory.mshistory.domain.dto.response.HistoryResponseDTO;
import br.com.compass.sprint6.mshistory.mshistory.domain.model.History;
import br.com.compass.sprint6.mshistory.mshistory.framework.adapter.out.database.HistoryRepository;
import br.com.compass.sprint6.mshistory.mshistory.framework.exceptions.response.ExceptionResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

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
    void shouldVerify_findAll() {
        List<History> history = List.of(new History());
        List<HistoryResponseDTO> response = List.of(new HistoryResponseDTO());

        Mockito.when(repository.findAll(Sort.by(Sort.Direction.DESC, "date"))).thenReturn(history);
        Mockito.when(assembler.toCollectionModel(history)).thenReturn(response);

        List<HistoryResponseDTO> all = service.findAll();

        assertEquals(history.get(0).getTotal(), all.get(0).getTotal());
    }

    @Test
    void shouldVerify_findAllDate() {
        List<History> history = List.of(new History());
        List<HistoryResponseDTO> response = List.of(new HistoryResponseDTO());
        LocalDate now = LocalDate.now();
        LocalDate of = LocalDate.of(2023, 01, 24);

        Mockito.when(repository.findByDateBetween(of,now)).thenReturn(history);
        Mockito.when(assembler.toCollectionModel(history)).thenReturn(response);

        List<HistoryResponseDTO> all = service.findAllDate(of, now);

        assertEquals(history.get(0).getTotal(), all.get(0).getTotal());
    }

    @Test
    void shouldVerify_verifyFindAllDate() {
        LocalDate now = LocalDate.now();
        LocalDate of = LocalDate.of(2023, 01, 24);

        List<HistoryResponseDTO> verify = service.verify(now, of);

        verify(repository).findByDateBetween(any(),any());
    }

    @Test
    void shouldVerify_verifyFindAll() {
        LocalDate of = LocalDate.of(2023, 01, 24);

        List<HistoryResponseDTO> verify = service.verify(null, of);

        verify(repository).findAll(Sort.by(Sort.Direction.DESC, "date"));
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
