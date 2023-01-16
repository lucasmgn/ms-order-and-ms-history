package br.com.compass.sprint6.msOrder.handler;

import br.com.compass.sprint6.msOrder.exceptions.handler.RestExceptionHandler;
import br.com.compass.sprint6.msOrder.exceptions.response.InvalidDateException;
import br.com.compass.sprint6.msOrder.exceptions.response.ItemNotFoundException;
import br.com.compass.sprint6.msOrder.exceptions.response.OrderNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RestExceptionHandlerTest {

    @InjectMocks
    private RestExceptionHandler handler;

    @Test
    void handleMissingServletRequestParameter() {
        Exception ex = new Exception();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, handler.handleAllExceptions(ex).getStatusCode());
    }

    @Test
    void orderNotFoundExceptionTest() {
        OrderNotFoundException ex = new OrderNotFoundException();
        assertEquals(HttpStatus.NOT_FOUND, handler.handleOrderNotFoundException(ex).getStatusCode());
    }

    @Test
    void itemNotFoundExceptionTest() {
        ItemNotFoundException ex = new ItemNotFoundException();
        assertEquals(HttpStatus.NOT_FOUND, handler.handleItemNotFoundException(ex).getStatusCode());
    }

    @Test
    void invalidDateExceptionTest() {
        InvalidDateException ex = new InvalidDateException();
        assertEquals(HttpStatus.BAD_REQUEST, handler.handleInvalidDateException(ex).getStatusCode());
    }



}
