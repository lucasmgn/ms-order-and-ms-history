package br.com.compass.sprint6.msOrder.service.dto.response;

import lombok.Data;

@Data
public class AddressResponseViaCepDTO {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
}
