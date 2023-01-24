package br.com.compass.sprint6.mshistory.mshistory.application.service.mapper;

import br.com.compass.sprint6.mshistory.mshistory.domain.dto.request.HistoryRequestDTO;
import br.com.compass.sprint6.mshistory.mshistory.domain.model.History;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HistoryInputDisassembler {
    private final ModelMapper modelMapper;

    public History toDomainObject(HistoryRequestDTO historyRequestDTO) {
        return modelMapper.map(historyRequestDTO, History.class);
    }

    public void copyToDomainObject(HistoryRequestDTO historyRequestDTO, History history) {
        modelMapper.map(historyRequestDTO, history);
    }
}
