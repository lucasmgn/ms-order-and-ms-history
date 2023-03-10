package br.com.compass.sprint6.mshistory.mshistory.framework.adapter.in.rest;

import br.com.compass.sprint6.mshistory.mshistory.application.service.HistoryService;
import br.com.compass.sprint6.mshistory.mshistory.domain.dto.response.HistoryResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService service;

    @GetMapping("/api/history/")
    public ResponseEntity<List<HistoryResponseDTO>> findAllDate(@RequestParam(required = false, name = "inicio") LocalDate inicio,
                                                            @RequestParam(required = false, name = "fim") LocalDate fim) {
        log.info("Listando History...");
        List<HistoryResponseDTO> responsePage = service.verify(inicio,fim);
        return ResponseEntity.status(HttpStatus.OK).body(responsePage);
    }
}
