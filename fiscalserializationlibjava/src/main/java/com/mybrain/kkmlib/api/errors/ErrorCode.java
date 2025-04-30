package com.mybrain.kkmlib.api.errors;

public enum ErrorCode {
    INVALID_HEADER_SIZE("Размер заголовка слишком мал, обратитесь к разработчику этой библиотеки."),
    UNKNOWN_TICKET_AD_TYPE("Вы выбрали не верный тип рекламных текстов, воспользуйтесь перечислением TicketAdType."),

    PAYLOAD_NOT_RESULT("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: не сообщил результат обработки запроса."),
    PAYLOAD_NOT_COMMAND("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: не сообщил команду на которую отправил ответ."),
    UNKNOWN_OFD_TICKET_AD_TYPE("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: прислал неизвестный тип рекламных текстов."),
    PAYLOAD_BAD_COMMAND("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: отправил на ваш запрос другой номер команды."),

    UNKNOWN_ERROR("Неизвестная ошибка.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}