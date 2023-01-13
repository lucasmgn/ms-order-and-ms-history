package br.com.compass.sprint6.msOrder.service.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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

    @NotBlank
    private String name;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate creation;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate expiration;

    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    @NotBlank
    private String description;
}
