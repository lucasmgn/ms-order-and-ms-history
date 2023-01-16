package br.com.compass.sprint6.msOrder.service;

import br.com.compass.sprint6.msOrder.entities.Order;
import br.com.compass.sprint6.msOrder.exceptions.response.OrderNotFoundException;
import br.com.compass.sprint6.msOrder.repository.AddressRepository;
import br.com.compass.sprint6.msOrder.repository.ItemRepository;
import br.com.compass.sprint6.msOrder.repository.OrderRepository;
import br.com.compass.sprint6.msOrder.service.assembler.OrderDTOAssembler;
import br.com.compass.sprint6.msOrder.service.assembler.OrderInputDisassembler;
import br.com.compass.sprint6.msOrder.service.dto.request.OrderRequestDTO;
import br.com.compass.sprint6.msOrder.service.dto.request.OrderResumeRequestDTO;
import br.com.compass.sprint6.msOrder.service.dto.response.AddressResponseViaCepDTO;
import br.com.compass.sprint6.msOrder.service.dto.response.OrderResponseDTO;
import br.com.compass.sprint6.msOrder.utils.CreateObject;
import br.com.compass.sprint6.msOrder.viacep.ViaCepClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    public static final Long ID = 1L;
    @InjectMocks
    private OrderService service;
    @Mock
    private OrderRepository repository;
    @Mock
    private OrderDTOAssembler assembler;
    @Mock
    private OrderInputDisassembler disassembler;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private ItemService itemService;
    @Mock
    private Pageable pageable;
    @Mock
    private ViaCepClient client;
    @InjectMocks
    private CreateObject createObject;

    @Test
    void shouldDeleteOrder_success() {
        service.delete(ID);
        verify(repository).deleteById(any());
    }

    @Test
    void shouldDeleteOrder_errorOrderNotFoundException() {
        service.delete(ID);
        verify(repository).deleteById(any());

        doThrow(new EmptyResultDataAccessException(1)).when(repository).deleteById(any());
        Assertions.assertThrows(OrderNotFoundException.class, () -> service.delete(ID));
    }

    @Test
    void shouldFindOrderById_NotFound() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> service.findBy(ID));
    }

    @Test
    void shouldCreateOrder_success() {
        OrderRequestDTO request = createObject.getOderRequestDTO();
        OrderResponseDTO response = createObject.getOderResponseDTO();
        AddressResponseViaCepDTO addressResponseViaCepDTO = new AddressResponseViaCepDTO();
        Order order = new Order();

        Mockito.when(client.find(request.getAddress().getCep())).thenReturn(addressResponseViaCepDTO);
        Mockito.when(disassembler.toDomainObject(any())).thenReturn(order);
        Mockito.when(repository.save(any())).thenReturn(order);
        Mockito.when(assembler.toModel(any())).thenReturn(response);

        OrderResponseDTO orderResponseDTO = service.create(request);
        assertEquals(response.getCpf(), orderResponseDTO.getCpf());
        verify(repository).save(any());
    }

    @Test
    void shouldFindAllOrder_success(){
        Page<Order> shipsPage = new PageImpl<>(List.of(new Order()));
        List<OrderResponseDTO> orderResponseDTOs = List.of(createObject.getOderResponseDTO());

        Mockito.when(repository.findAll(any(Pageable.class))).thenReturn(shipsPage);
        Mockito.when(assembler.toCollectionModel(shipsPage.getContent())).thenReturn(orderResponseDTOs);

        List<OrderResponseDTO> all = service.findAll(pageable);

        Assertions.assertEquals(orderResponseDTOs.get(0).getCpf(), all.get(0).getCpf());
    }

    @Test
    void findBy_sucess(){
        Order order = new Order();
        OrderResponseDTO response = createObject.getOderResponseDTO();
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(order));
        Mockito.when(assembler.toModel(any())).thenReturn(response);

        OrderResponseDTO orderResponseDTO = service.findBy(ID);

        assertEquals(response.getCpf(), orderResponseDTO.getCpf());
    }

    @Test
    void shouldUpdateOrder_success() {
        OrderResumeRequestDTO orderRequestDTO = new OrderResumeRequestDTO();
        orderRequestDTO.setTotal(new BigDecimal("10"));
        OrderResponseDTO response = new OrderResponseDTO();
        Order order = new Order();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(order));
        Mockito.when(repository.save(any())).thenReturn(order);
        Mockito.when(assembler.toModel(any())).thenReturn(response);

        OrderResponseDTO update = service.update(ID, orderRequestDTO);
        assertEquals(response.getCpf(), update.getCpf());
        verify(repository).save(any());
    }

    @Test
    void shouldFetchOrFail_Error() {
        assertThrows(OrderNotFoundException.class, () -> service.fetchOrFail("09591781555"));
    }

    @Test
    void shouldVerify_FindAll() {
        List<Order> order = List.of(new Order());
        List<OrderResponseDTO> response = List.of(new OrderResponseDTO());
        Page<Order> a = new PageImpl<>(order);

        Mockito.when(repository.findAll(any(Pageable.class))).thenReturn(a);
        Mockito.when(assembler.toCollectionModel(a.getContent())).thenReturn(response);

        List<OrderResponseDTO> verify = service.verify(pageable, null);

        assertEquals(verify, response);
    }
}
