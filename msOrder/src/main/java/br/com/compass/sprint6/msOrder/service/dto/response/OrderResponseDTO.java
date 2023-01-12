package br.com.compass.sprint6.msOrder.service.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderResponseDTO {

    private Long id;

    private String cpf;

    private List<ItemResponseDTO> items;

    private BigDecimal total;

    private AddressResponseDTO address;
}
