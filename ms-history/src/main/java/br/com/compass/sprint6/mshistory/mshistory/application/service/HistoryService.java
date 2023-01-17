package br.com.compass.sprint6.mshistory.mshistory.application.service;

import br.com.compass.sprint6.mshistory.mshistory.application.service.assembler.HistoryDTOAssembler;
import br.com.compass.sprint6.mshistory.mshistory.domain.dto.response.HistoryResponseDTO;
import br.com.compass.sprint6.mshistory.mshistory.domain.model.History;
import br.com.compass.sprint6.mshistory.mshistory.framework.adapter.out.database.HistoryRepository;
import br.com.compass.sprint6.mshistory.mshistory.framework.exceptions.response.HistoryNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository repository;

    private final HistoryDTOAssembler assembler;

    @Transactional
    public History create(History history) {
        log.info("Chamando método create (salvando no repository) - Service History");
        return repository.save(history);
    }

    public List<HistoryResponseDTO> findAll(){
        log.info("Chamando método findAll - Service History");
        List<History> histories = repository.findAll();
        return assembler.toCollectionModel(histories);
    }

    public HistoryResponseDTO findBy(String cod){
        log.info("Chamando método findAll - Service History");
        History history = fetchOrFail(cod);
        return assembler.toModel(history);
    }

    private History fetchOrFail(String cod) {
        log.info("Chamando método fetchOrFail (ID) - Service History");
        return repository.findById(cod).orElseThrow(HistoryNotFoundException::new);
    }
}
