package br.com.compass.sprint6.msOrder.domain.dto.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResumeRequestDTO {

    @CPF
    private String cpf;

    private BigDecimal total;

    @Valid
    private AddressResumeRequestDTO address;
}
