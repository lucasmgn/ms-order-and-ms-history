package br.com.compass.sprint6.msOrder.exceptions.response;

import br.com.compass.sprint6.msOrder.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class ItemNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;
    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public ItemNotFoundException() {
        super(ErrorCode.ITEM_NOT_FOUND.name());
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.errorCode = ErrorCode.ITEM_NOT_FOUND;
        this.details = ErrorCode.ITEM_NOT_FOUND.getMessage();

    }
}
