package br.com.compass.sprint6.msOrder.rest;

import br.com.compass.sprint6.msOrder.application.service.ItemService;
import br.com.compass.sprint6.msOrder.application.service.OrderService;
import br.com.compass.sprint6.msOrder.domain.dto.request.OrderRequestDTO;
import br.com.compass.sprint6.msOrder.domain.dto.request.OrderUpdateRequest;
import br.com.compass.sprint6.msOrder.domain.dto.response.ItemResumeResponseDTO;
import br.com.compass.sprint6.msOrder.domain.dto.response.OrderResponseDTO;
import br.com.compass.sprint6.msOrder.framework.adapter.in.rest.OrderController;
import br.com.compass.sprint6.msOrder.utils.CreateObject;
import br.com.compass.sprint6.msOrder.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = OrderController.class)
class OderControllerTest {
    public static final String BASE_URL = "/api/pedidos/";
    public static final String BASE_URL_ITEMS = "/api/itens/1";
    public static final String ID_URL = BASE_URL + "1";

    @MockBean
    private OrderService service;

    @MockBean
    private ItemService itemService;

    @InjectMocks
    private CreateObject createObject;
    @Autowired
    private MockMvc mvc;

    @Test
    void findAll() throws Exception {
        List<OrderResponseDTO> orders = List.of(new OrderResponseDTO());
        when(service.findAll(any(Pageable.class))).thenReturn(orders);
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse resposta = result.getResponse();
        assertEquals(HttpStatus.OK.value(), resposta.getStatus());
    }

    @Test
    void findById() throws Exception {
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void updateItens() throws Exception {
        ItemResumeResponseDTO request = createObject.getItemResumeResponseDTO();
        String input = TestUtils.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.patch(BASE_URL_ITEMS)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void create() throws Exception {
        OrderRequestDTO request = createObject.getOderRequestDTO();
        String input = TestUtils.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void update() throws Exception {
        OrderUpdateRequest request = new OrderUpdateRequest();
        String input = TestUtils.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.put(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void delete() throws Exception {
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.delete(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

}
