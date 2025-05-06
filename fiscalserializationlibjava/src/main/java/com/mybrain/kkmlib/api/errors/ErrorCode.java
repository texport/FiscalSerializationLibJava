package com.mybrain.kkmlib.api.errors;

public enum ErrorCode {
    INVALID_HEADER_SIZE("Размер заголовка слишком мал, обратитесь к разработчику этой библиотеки."),

    PAYLOAD_NOT_RESULT("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: не сообщил результат обработки запроса."),
    PAYLOAD_NOT_COMMAND("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: не сообщил команду на которую отправил ответ."),
    PAYLOAD_BAD_COMMAND("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: отправил на ваш запрос другой номер команды."),
    
    /*
     * Ошибки с рекламными текстами
     */
    USER_UNKNOWN_TICKET_AD_TYPE("Вы выбрали не верный тип рекламных текстов, воспользуйтесь перечислением TicketAdType."),

    OFD_TICKET_AD_INFO_NOT_FOUND("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: при отправке рекламных текстов не передал Info."),
    OFD_TICKET_AD_TYPE_NOT_FOUND("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: при отправке рекламных текстов не передал Type."),
    OFD_TICKET_AD_VERSION_NOT_FOUND("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: при отправке рекламных текстов не передал Version."),
    OFD_TICKET_AD_TEXT_NOT_FOUND("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: при отправке рекламных текстов не передал Text."),
    OFD_TICKET_AD_TYPE_UNKNOWN("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: прислал неизвестный тип рекламных текстов."),

    /*
     * Ошибки с информацией о ККМ
     */
    OFD_REG_INFO_NOT_FOUND("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: при отправке сервисной части не передал RegInfo."),
    OFD_KKM_REG_INFO_NOT_FOUND("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: при отправке сервисной части не передал KkmRegInfo."),
    OFD_ORG_REG_INFO_NOT_FOUND("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: при отправке сервисной части не передал OrgRegInfo."),
    OFD_KKM_REG_INFO_KGD_ID_NOT_FOUND("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: при отправке сервисной части не передал KgdKkmId."),
    OFD_KKM_REG_INFO_SERIAL_NUMBER_NOT_FOUND("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: при отправке сервисной части не передал SerialNumber."),
    OFD_KKM_REG_INFO_KKM_ID_NOT_FOUND("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: при отправке сервисной части не передал KkmId."),
    OFD_ORG_REG_INFO_TITLE_NOT_FOUND("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: при отправке сервисной части не передал OrgTitle."),
    OFD_ORG_REG_INFO_ADDRESS_NOT_FOUND("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: при отправке сервисной части не передал OrgAddress."),
    OFD_ORG_REG_INFO_IIN_NOT_FOUND("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: при отправке сервисной части не передал Iin/Bin."),
    OFD_ORG_REG_INFO_OKED_NOT_FOUND("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: при отправке сервисной части не передал Oked."),
    OFD_POS_REG_INFO_TITLE_NOT_FOUND("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: при отправке сервисной части не передал PosTitle."),
    OFD_POS_REG_INFO_ADDRESS_NOT_FOUND("Сервер ОФД НАРУШИЛ ПРОТОКОЛ: при отправке сервисной части не передал PosAddress."),

    ARGUMENT_NULL("%s не может быть null."),
    ARGUMENT_EQUAL_LENGTH("%s должен быть равен %d."),
    ARGUMENT_STRING_NUMBER("%s должен содержать только цифры."),
    ARGUMENT_SPACES_BEGIN_END("%s не может иметь пробелы в начале или конце."),
    ARGUMENT_EMPTY("%s не может быть пустым."),
    ARGUMENT_NOT_LONGER_COULD("%s не может быть больше чем %d символов."),
    ARGUMENT_NOT_SPACES("%s не может состоять только из пробелов."),

    NETWORK_IO_ERROR("Ошибка сетевого соединения с сервером ОФД."),
    UNEXPECTED_ERROR("Неожиданная ошибка при обработке запроса."),
    HANDLER_NOT_FOUND("Обработчик для команды не найден или не зарегистрирован."),
    UNKNOWN_ERROR("Неизвестная ошибка.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }

    public String format(Object... args) {
        return String.format(message, args);
    }
}