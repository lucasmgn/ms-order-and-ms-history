package br.com.compass.sprint6.mshistory.mshistory.service.assembler;

import br.com.compass.sprint6.mshistory.mshistory.application.service.mapper.HistoryInputDisassembler;
import br.com.compass.sprint6.mshistory.mshistory.domain.dto.request.HistoryRequestDTO;
import br.com.compass.sprint6.mshistory.mshistory.domain.model.History;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class HistoryInputDisassemblerTest {
    @InjectMocks
    private HistoryInputDisassembler service;

    @Spy
    private ModelMapper mapper;

    @Test
    void toDomainObject_Sucess(){
        HistoryRequestDTO requestDTO = new HistoryRequestDTO();
        requestDTO.setTotal(new BigDecimal("1"));
        History history = service.toDomainObject(requestDTO);
        Assertions.assertEquals(history.getTotal(), requestDTO.getTotal());
    }

}
