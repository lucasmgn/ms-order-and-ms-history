package br.com.compass.sprint6.msOrder.framework.adapter.in.rest;

import br.com.compass.sprint6.msOrder.application.service.ItemService;
import br.com.compass.sprint6.msOrder.application.service.OrderService;
import br.com.compass.sprint6.msOrder.domain.dto.request.OrderRequestDTO;
import br.com.compass.sprint6.msOrder.domain.dto.request.OrderUpdateRequest;
import br.com.compass.sprint6.msOrder.domain.dto.response.ItemResumeResponseDTO;
import br.com.compass.sprint6.msOrder.domain.dto.response.OrderResponseDTO;
import br.com.compass.sprint6.msOrder.domain.model.Item;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    private final ItemService itemService;

    @PostMapping("/api/pedidos/")
    public ResponseEntity<OrderResponseDTO> create(@RequestBody @Valid OrderRequestDTO request) {
        log.info("Criando uma nova Order...");
        OrderResponseDTO response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/api/pedidos/")
    public ResponseEntity<List<OrderResponseDTO>> findAll(@RequestParam(required = false, name = "cpf") String cpf, @PageableDefault(size = 10) Pageable pagination) {
        log.info("Listando Order com página de {} registros...", pagination.getPageSize());
        List<OrderResponseDTO> responsePage = service.verify(pagination, cpf);
        return ResponseEntity.status(HttpStatus.OK).body(responsePage);
    }

    @GetMapping("/api/pedidos/{id}")
    public ResponseEntity<OrderResponseDTO> findBy(@PathVariable("id") Long id){
        log.info("Buscando Order...");
        OrderResponseDTO pierResponseDTO = service.findBy(id);
        return ResponseEntity.status(HttpStatus.OK).body(pierResponseDTO);
    }

    @PatchMapping("/api/itens/{id}")
    public ResponseEntity<ItemResumeResponseDTO> updateItens(@PathVariable("id") Long id, @RequestBody Map<String, Object> fields){
        log.info("Atualizando Itens...");
        Item item = itemService.fetchOrFail(id);
        itemService.merge(fields,item);
        ItemResumeResponseDTO itemResponseDTO = itemService.updateItem(id);
        return ResponseEntity.status(HttpStatus.OK).body(itemResponseDTO);
    }

    @PutMapping("/api/pedidos/{id}")
    public ResponseEntity<OrderResponseDTO> update(@PathVariable("id") Long id, @RequestBody @Valid OrderUpdateRequest request){
        log.info("Atualizando Order...");
        OrderResponseDTO orderResponseDTO = service.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDTO);
    }

    @DeleteMapping("/api/pedidos/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id") Long id){
        log.info("Excluindo uma Order...");
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
