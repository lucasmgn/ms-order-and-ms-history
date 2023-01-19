package br.com.compass.sprint6.mshistory.mshistory.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class HistoryResponseDTO {

    private String cod;
    private Long idOrder;
    private BigDecimal total;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;
}
