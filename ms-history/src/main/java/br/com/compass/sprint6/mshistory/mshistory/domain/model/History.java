package br.com.compass.sprint6.mshistory.mshistory.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document
@Data
public class History {

    @Id
    private String cod;
    private Long id;
    private BigDecimal total;
    private LocalDate date;

}
