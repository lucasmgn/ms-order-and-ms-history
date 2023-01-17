package br.com.compass.sprint6.msOrder.framework.exceptions.response;

import br.com.compass.sprint6.msOrder.domain.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class InvalidDateException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;
    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public InvalidDateException() {
        super(ErrorCode.INVALID_DATE_EXCEPTION.name());
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorCode = ErrorCode.INVALID_DATE_EXCEPTION;
        this.details = ErrorCode.INVALID_DATE_EXCEPTION.getMessage();
    }
}
