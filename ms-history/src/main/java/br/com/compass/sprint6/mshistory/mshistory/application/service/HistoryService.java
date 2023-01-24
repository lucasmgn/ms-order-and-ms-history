package br.com.compass.sprint6.mshistory.mshistory.application.service;

import br.com.compass.sprint6.mshistory.mshistory.application.service.mapper.HistoryDTOAssembler;
import br.com.compass.sprint6.mshistory.mshistory.application.service.mapper.HistoryInputDisassembler;
import br.com.compass.sprint6.mshistory.mshistory.domain.dto.request.HistoryRequestDTO;
import br.com.compass.sprint6.mshistory.mshistory.domain.dto.response.HistoryResponseDTO;
import br.com.compass.sprint6.mshistory.mshistory.domain.model.History;
import br.com.compass.sprint6.mshistory.mshistory.framework.adapter.out.database.HistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository repository;

    private final HistoryDTOAssembler assembler;

    private final HistoryInputDisassembler disassembler;

    @Transactional
    public History create(History history) {
        log.info("Chamando método create (salvando no repository) - Service History");
        return repository.save(history);
    }

    @Transactional
    public HistoryResponseDTO create(HistoryRequestDTO historyRequestDTO) {
        log.info("Chamando método create (salvando no repository) - Service History");
        History history = disassembler.toDomainObject(historyRequestDTO);
        history.setIdOrder(historyRequestDTO.getId());
        history.setDate(LocalDate.now());
        create(history);
        return assembler.toModel(history);
    }

    public List<HistoryResponseDTO> findAll(){
        log.info("Chamando método findAll - Service History");
        List<History> histories = repository.findAll(Sort.by(Sort.Direction.DESC, "date"));
        return assembler.toCollectionModel(histories);
    }

    public List<HistoryResponseDTO> findAllDate(LocalDate inicio, LocalDate fim){
        log.info("Chamando método findAllDate - Service History");
        List<History> histories = repository.findByDateBetween(inicio,fim);
        return assembler.toCollectionModel(histories);
    }

    public List<HistoryResponseDTO> verify(LocalDate inicio, LocalDate fim){
        log.info("Chamando método verify - Service History");
        if(inicio == null || fim == null){
            return findAll();
        }else{
            return findAllDate(inicio, fim);
        }
    }
}
