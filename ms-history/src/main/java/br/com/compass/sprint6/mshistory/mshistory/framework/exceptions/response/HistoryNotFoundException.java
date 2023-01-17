package br.com.compass.sprint6.mshistory.mshistory.framework.exceptions.response;

import br.com.compass.sprint6.mshistory.mshistory.domain.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class HistoryNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;
    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public HistoryNotFoundException() {
        super(ErrorCode.HISTORY_NOT_FOUND.name());
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorCode = ErrorCode.HISTORY_NOT_FOUND;
        this.details = ErrorCode.HISTORY_NOT_FOUND.getMessage();
    }
}
