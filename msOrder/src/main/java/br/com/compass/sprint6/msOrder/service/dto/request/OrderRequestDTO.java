package br.com.compass.sprint6.msOrder.service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

    @CPF
    private String cpf;

    @NotNull
    private List<ItemRequestDTO> items;

    @NotNull
    private BigDecimal total;

    private AddressResumeRequestDTO address;
}
