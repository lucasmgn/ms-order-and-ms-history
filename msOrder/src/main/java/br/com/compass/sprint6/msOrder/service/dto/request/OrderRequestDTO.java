package br.com.compass.sprint6.msOrder.service.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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

    @Valid
    private List<ItemRequestDTO> items;

    @NotNull
    @PositiveOrZero
    private BigDecimal total;

    @NotNull
    private AddressResumeRequestDTO address;
}
