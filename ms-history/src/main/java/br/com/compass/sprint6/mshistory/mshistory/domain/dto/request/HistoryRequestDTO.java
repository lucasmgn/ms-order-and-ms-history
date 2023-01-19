package br.com.compass.sprint6.mshistory.mshistory.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryRequestDTO {

    private Long idOrder;
    private BigDecimal total;
    private LocalDate date;
}
