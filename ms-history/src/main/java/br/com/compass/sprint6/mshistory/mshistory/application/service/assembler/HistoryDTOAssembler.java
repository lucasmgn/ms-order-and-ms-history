package br.com.compass.sprint6.mshistory.mshistory.application.service.assembler;

import br.com.compass.sprint6.mshistory.mshistory.domain.dto.response.HistoryResponseDTO;
import br.com.compass.sprint6.mshistory.mshistory.domain.model.History;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HistoryDTOAssembler {

    private final ModelMapper modelMapper;

    public HistoryResponseDTO toModel(History history){
        return modelMapper.map(history,HistoryResponseDTO.class);
    }

    public List<HistoryResponseDTO> toCollectionModel(List<History> items){
        return items.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
