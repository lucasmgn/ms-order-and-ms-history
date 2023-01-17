package br.com.compass.sprint6.msOrder.assembler;

import br.com.compass.sprint6.msOrder.domain.model.Order;
import br.com.compass.sprint6.msOrder.application.service.assembler.OrderInputDisassembler;
import br.com.compass.sprint6.msOrder.domain.dto.request.OrderRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class OrderInputDisassemblerTest {

    @InjectMocks
    private OrderInputDisassembler service;

    @Spy
    private ModelMapper mapper;

    @Test
    void toDomainObject_Sucess(){
        OrderRequestDTO orderRequestDTO = getOrderRequestDTO();
        Order order = service.toDomainObject(orderRequestDTO);
        Assertions.assertEquals(order.getCpf(), orderRequestDTO.getCpf());
    }

    private static OrderRequestDTO getOrderRequestDTO(){
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setCpf("09591781555");
        return orderRequestDTO;
    }
}
