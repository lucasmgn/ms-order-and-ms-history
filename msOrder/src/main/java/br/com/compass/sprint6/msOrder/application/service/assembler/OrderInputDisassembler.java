package br.com.compass.sprint6.msOrder.application.service.assembler;
import br.com.compass.sprint6.msOrder.domain.model.Order;
import br.com.compass.sprint6.msOrder.domain.dto.request.OrderRequestDTO;
import br.com.compass.sprint6.msOrder.domain.dto.request.OrderResumeRequestDTO;
import br.com.compass.sprint6.msOrder.domain.dto.request.OrderUpdateRequest;
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

    public void copyToDomainObject(OrderUpdateRequest orderRequestDTO, Order order){
        modelMapper.map(orderRequestDTO, order);
    }
}
