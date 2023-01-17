package br.com.compass.sprint6.msOrder.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResumeRequestDTO {

    @NotBlank
    private String number;

    @NotBlank
    private String cep;

    private String street;

    private String neighborhood;

    private String city;

    private String state;
}
