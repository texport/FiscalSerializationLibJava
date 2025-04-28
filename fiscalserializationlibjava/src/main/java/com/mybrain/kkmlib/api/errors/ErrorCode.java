package com.mybrain.kkmlib.api.errors;

public enum ErrorCode {
    INVALID_HEADER_SIZE("Размер заголовка слишком мал, обратитесь к разработчику этой библиотеки."),
    UNKNOWN_ERROR("Неизвестная ошибка.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}