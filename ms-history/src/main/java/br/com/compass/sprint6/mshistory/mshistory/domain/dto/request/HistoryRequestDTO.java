package br.com.compass.sprint6.mshistory.mshistory.domain.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class HistoryRequestDTO {

    private Long id;
    private BigDecimal total;
    private LocalDate date;
}
