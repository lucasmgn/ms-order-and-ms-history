package br.com.compass.sprint6.mshistory.mshistory.framework.adapter.in.rest;

import br.com.compass.sprint6.mshistory.mshistory.application.service.HistoryService;
import br.com.compass.sprint6.mshistory.mshistory.domain.dto.request.HistoryRequestDTO;
import br.com.compass.sprint6.mshistory.mshistory.domain.dto.response.HistoryResponseDTO;
import br.com.compass.sprint6.mshistory.mshistory.domain.model.History;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService service;

    @GetMapping("/api/history/")
    public ResponseEntity<List<HistoryResponseDTO>> findAll() {
        log.info("Listando History...");
        List<HistoryResponseDTO> responsePage = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(responsePage);
    }

    @PostMapping("/api/history/")
    public ResponseEntity<HistoryResponseDTO> create(@RequestBody HistoryRequestDTO historyRequestDTO) {
        log.info("Criando History...");
        HistoryResponseDTO historyResponseDTO = service.create(historyRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(historyResponseDTO);
    }
}
