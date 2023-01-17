package br.com.compass.sprint6.mshistory.mshistory.domain.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class HistoryResponseDTO {

    private String cod;
    private Long id;
    private BigDecimal total;
    private LocalDate date;
}
