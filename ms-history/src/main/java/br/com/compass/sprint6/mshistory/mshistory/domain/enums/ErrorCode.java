package br.com.compass.sprint6.mshistory.mshistory.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("Ocorreu um erro interno.");

    private final String message;
}
