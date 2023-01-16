package br.com.compass.sprint6.msOrder.utils;

import br.com.compass.sprint6.msOrder.entities.Item;
import br.com.compass.sprint6.msOrder.entities.Order;
import br.com.compass.sprint6.msOrder.service.assembler.ItemDTOAssembler;
import br.com.compass.sprint6.msOrder.service.assembler.ItemInputDisassembler;
import br.com.compass.sprint6.msOrder.service.assembler.OrderInputDisassembler;
import br.com.compass.sprint6.msOrder.service.dto.request.AddressResumeRequestDTO;
import br.com.compass.sprint6.msOrder.service.dto.request.ItemRequestDTO;
import br.com.compass.sprint6.msOrder.service.dto.request.OrderRequestDTO;
import br.com.compass.sprint6.msOrder.service.dto.response.AddressResponseDTO;
import br.com.compass.sprint6.msOrder.service.dto.response.ItemResponseDTO;
import br.com.compass.sprint6.msOrder.service.dto.response.ItemResumeResponseDTO;
import br.com.compass.sprint6.msOrder.service.dto.response.OrderResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CreateObject {

    private ItemDTOAssembler assembler;

    private OrderInputDisassembler disassembler;

    public Item item() {
        Item i = new Item();
        i.setName("Test");
        i.setCreation(LocalDate.of(2002, 01, 02));
        i.setExpiration(LocalDate.of(2002, 01, 03));
        i.setPrice(new BigDecimal("12"));
        i.setDescription("test");
        return i;
    }

    public ItemResumeResponseDTO getItemResumeResponseDTO() {
        ItemResumeResponseDTO responseDTO = new ItemResumeResponseDTO();
        responseDTO.setName("test");
        responseDTO.setPrice(new BigDecimal("15"));
        responseDTO.setCreation(LocalDate.of(2002, 12, 01));
        responseDTO.setExpiration(LocalDate.of(2003, 02, 12));
        responseDTO.setDescription("Test");
        return responseDTO;
    }

    public ItemResponseDTO itemResponseDTO() {
        return assembler.toModel(item());
    }

    public OrderResponseDTO getOderResponseDTO() {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setCpf("095.917.815-55");
        orderResponseDTO.setAddress(getAddressResponseDTO());
        orderResponseDTO.setItems(List.of(getItemsResponseDTO()));
        orderResponseDTO.setTotal(new BigDecimal("5000"));
        return orderResponseDTO;
    }

    public OrderRequestDTO getOderRequestDTO() {
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        requestDTO.setCpf("095.917.815-55");
        requestDTO.setAddress(getAddressResumeResponseDTO());
        requestDTO.setItems(List.of(getItemsRequestDTO()));
        requestDTO.setTotal(new BigDecimal("5000"));
        return requestDTO;
    }

    public Order getOrder(){
        return disassembler.toDomainObject(getOderRequestDTO());
    }

    public AddressResumeRequestDTO getAddressResumeResponseDTO() {
        return AddressResumeRequestDTO.builder()
                .cep("40430390")
                .city("SA")
                .neighborhood("Vila Rui Barbosa")
                .street("Rua sete de abril")
                .number("9A")
                .build();
    }

    public AddressResponseDTO getAddressResponseDTO() {
        return AddressResponseDTO.builder()
                .cep("40430390")
                .city("SA")
                .neighborhood("Vila Rui Barbosa")
                .street("Rua sete de abril")
                .number("9A")
                .build();
    }

    public ItemResponseDTO getItemsResponseDTO() {
        ItemResponseDTO responseDTO = new ItemResponseDTO();
        responseDTO.setName("test");
        responseDTO.setPrice(new BigDecimal("15"));
        responseDTO.setCreation(LocalDate.of(2002, 12, 01));
        responseDTO.setExpiration(LocalDate.of(2003, 02, 12));
        responseDTO.setDescription("Test");
        return responseDTO;
    }

    private ItemRequestDTO getItemsRequestDTO() {
        return ItemRequestDTO.builder()
                .name("test")
                .price(new BigDecimal("15"))
                .creation(LocalDate.of(2002, 12, 01))
                .expiration(LocalDate.of(2003, 02, 12))
                .description("Test")
                .build();
    }
}
