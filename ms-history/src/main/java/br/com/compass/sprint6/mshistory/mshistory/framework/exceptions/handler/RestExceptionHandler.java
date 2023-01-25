package br.com.compass.sprint6.mshistory.mshistory.framework.exceptions.handler;


import br.com.compass.sprint6.mshistory.mshistory.domain.enums.ErrorCode;
import br.com.compass.sprint6.mshistory.mshistory.framework.exceptions.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        log.error(ex.getMessage(), ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.INTERNAL_SERVER_ERROR, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);

    }
}
