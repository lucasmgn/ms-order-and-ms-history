package br.com.compass.sprint6.mshistory.mshistory.framework.exceptions.response;

import br.com.compass.sprint6.mshistory.mshistory.domain.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class ExceptionResponse {

    private final String code;

    private final String message;

    private final List<String> details;


    public ExceptionResponse(ErrorCode errorCode, Throwable ex) {
        this(errorCode, ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage());
    }

    public ExceptionResponse(ErrorCode errorCode, String details) {
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
        this.details = Collections.singletonList(details);
    }
}