package br.com.compass.sprint6.msOrder.assembler;

import br.com.compass.sprint6.msOrder.domain.model.Order;
import br.com.compass.sprint6.msOrder.application.service.mapper.OrderDTOAssembler;
import br.com.compass.sprint6.msOrder.domain.dto.response.OrderResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class OrderAssemblerTest {

    @InjectMocks
    private OrderDTOAssembler service;

    @Spy
    private ModelMapper mapper;

    @Test
    void toModelSucess(){
        Order order = getOrder();
        OrderResponseDTO orderResponseDTO = service.toModel(order);
        Assertions.assertEquals(order.getCpf(), orderResponseDTO.getCpf());
    }

    @Test
    void toColletionModelSucess(){
        List<Order> order = List.of(getOrder());
        List<OrderResponseDTO> orderResponseDTOS = service.toCollectionModel(order);
        Assertions.assertEquals(order.get(0).getCpf(), orderResponseDTOS.get(0).getCpf());
    }

    private static Order getOrder() {
        Order order = new Order();
        order.setCpf("09591781555");
        return order;
    }
}
