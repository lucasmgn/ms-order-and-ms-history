package br.com.compass.sprint6.mshistory.mshistory.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    BAD_REQUEST("Requisição inválida"),
    INVALID_PARAMETER("Parâmetro Inválido!"),
    INTERNAL_SERVER_ERROR("Ocorreu um erro interno."),
    HISTORY_NOT_FOUND("Histórico não encontrado!");

    private final String message;
}
