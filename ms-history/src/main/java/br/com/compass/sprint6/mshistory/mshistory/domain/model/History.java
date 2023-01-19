package br.com.compass.sprint6.mshistory.mshistory.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class History {

    @Id
    private String cod;
    private Long idOrder;
    private BigDecimal total;
    private LocalDate date;

}
