package br.com.compass.sprint6.mshistory.mshistory.service.assembler;

import br.com.compass.sprint6.mshistory.mshistory.application.service.assembler.HistoryDTOAssembler;
import br.com.compass.sprint6.mshistory.mshistory.domain.dto.response.HistoryResponseDTO;
import br.com.compass.sprint6.mshistory.mshistory.domain.model.History;
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
class HistoryDToAssemblerTest {

    @InjectMocks
    private HistoryDTOAssembler service;


    @Spy
    private ModelMapper mapper;

    @Test
    void toModelSucess(){
        History history = getHistory();
        HistoryResponseDTO historyResponseDTO = service.toModel(history);
        Assertions.assertEquals(history.getTotal(), historyResponseDTO.getTotal());
    }

    public History getHistory() {
        History h = new History();
        h.setTotal(new BigDecimal("1"));
        h.setDate(LocalDate.now());
        h.setIdOrder(1L);
        return h;
    }

    @Test
    void toColletionModelSucess(){
        List<History> history = List.of(getHistory());
        List<HistoryResponseDTO> historyResponseDTOS = service.toCollectionModel(history);
        Assertions.assertEquals(history.get(0).getTotal(), historyResponseDTOS.get(0).getTotal());
    }

}
