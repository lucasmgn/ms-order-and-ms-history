package br.com.compass.sprint6.msOrder.domain.dto.response;

import lombok.Data;

@Data
public class AddressResponseDTO {
    private Long id;
    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String state;
    private String cep;
}
