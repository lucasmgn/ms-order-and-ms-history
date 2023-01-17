package br.com.compass.sprint6.msOrder.framework.adapter.out.viacep;

import br.com.compass.sprint6.msOrder.domain.dto.response.AddressResponseViaCepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url= "https://viacep.com.br/ws/" , name = "viacep")
public interface ViaCepClient {

    @GetMapping(value = "{cep}/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    AddressResponseViaCepDTO find(@PathVariable("cep") String cep);
}