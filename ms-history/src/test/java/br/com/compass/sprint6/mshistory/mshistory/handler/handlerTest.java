package br.com.compass.sprint6.mshistory.mshistory.handler;

import br.com.compass.sprint6.mshistory.mshistory.framework.exceptions.handler.RestExceptionHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class handlerTest {

    @InjectMocks
    private RestExceptionHandler handler;

    @Test
    void shouldVerify_Exception() {
        Exception exception = new Exception();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, handler.handleAllExceptions(exception).getStatusCode());
    }
}
