package br.com.compass.sprint6.msOrder.service.assembler;
import br.com.compass.sprint6.msOrder.entities.Order;
import br.com.compass.sprint6.msOrder.service.dto.request.OrderRequestDTO;
import br.com.compass.sprint6.msOrder.service.dto.request.OrderResumeRequestDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderInputDisassembler {
    private final ModelMapper modelMapper;

    public Order toDomainObject(OrderRequestDTO orderRequestDTO) {
        return modelMapper.map(orderRequestDTO, Order.class);
    }

    public void copyToDomainObject(OrderRequestDTO orderRequestDTO, Order order){
        modelMapper.map(orderRequestDTO, order);
    }

    public void copyToDomainObject(OrderResumeRequestDTO orderRequestDTO, Order order){
        modelMapper.map(orderRequestDTO, order);
    }
}
