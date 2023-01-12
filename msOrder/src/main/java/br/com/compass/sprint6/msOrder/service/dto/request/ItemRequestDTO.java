package br.com.compass.sprint6.msOrder.service.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestDTO {

    private String name;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate creation;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate expiration;

    private BigDecimal price;

    private String description;
}
