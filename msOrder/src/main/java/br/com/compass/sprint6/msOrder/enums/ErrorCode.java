package br.com.compass.sprint6.msOrder.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    BAD_REQUEST("Requisição inválida"),
    INVALID_PARAMETER("Parâmetro Inválido!"),
    INTERNAL_SERVER_ERROR("Ocorreu um erro interno."),
    ORDER_NOT_FOUND("Pedido não encontrado!"),
    ITEM_NOT_FOUND("Item não encontrado!");

    private final String message;
}