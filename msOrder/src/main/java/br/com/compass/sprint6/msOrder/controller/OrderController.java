package br.com.compass.sprint6.msOrder.controller;

import br.com.compass.sprint6.msOrder.service.OrderService;
import br.com.compass.sprint6.msOrder.service.dto.request.OrderRequestDTO;
import br.com.compass.sprint6.msOrder.service.dto.response.OrderResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pedidos/")
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> create(@RequestBody @Valid OrderRequestDTO request) {
        log.info("Criando uma nova Order...");
        OrderResponseDTO response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAll(@RequestParam(required = false, name = "cpf") String cpf, @PageableDefault(size = 10) Pageable pagination) {
        log.info("Listando Order com página de {} registros...", pagination.getPageSize());
        List<OrderResponseDTO> responsePage = service.verify(pagination, cpf);
        return ResponseEntity.status(HttpStatus.OK).body(responsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> findBy(@PathVariable("id") Long id){
        log.info("Buscando Order...");
        OrderResponseDTO pierResponseDTO = service.findBy(id);
        return ResponseEntity.status(HttpStatus.OK).body(pierResponseDTO);
    }

    @PatchMapping("api/itens/{id}")
    public ResponseEntity<OrderResponseDTO> updateItens(@PathVariable("id") Long id, @RequestBody @Valid OrderRequestDTO request){
        log.info("Atualizando Pier por id...");
        OrderResponseDTO pierResponseDTO = service.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(pierResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> update(@PathVariable("id") Long id, @RequestBody @Valid OrderRequestDTO request){
        log.info("Atualizando Order...");
        OrderResponseDTO pierResponseDTO = service.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(pierResponseDTO);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id") Long id){
        log.info("Excluindo uma Order...");
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
