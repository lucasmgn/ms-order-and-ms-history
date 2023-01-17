package br.com.compass.sprint6.msOrder.framework.exceptions.response;

import br.com.compass.sprint6.msOrder.domain.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class OrderNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public OrderNotFoundException() {
        super(ErrorCode.ORDER_NOT_FOUND.name());
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.errorCode = ErrorCode.ORDER_NOT_FOUND;
        this.details = ErrorCode.ORDER_NOT_FOUND.getMessage();

    }
}
