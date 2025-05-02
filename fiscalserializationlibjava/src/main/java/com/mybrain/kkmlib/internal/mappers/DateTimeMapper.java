package com.mybrain.kkmlib.internal.mappers;

import com.mybrain.kkmlib.internal.utils.ValidationUtils;
import kz.kgdkkmproto.kkm.proto.Common;

import java.time.LocalDateTime;

public final class DateTimeMapper {

    /**
     * Метод для создания и возвращения структуры 'DateTime' в формате протокола
     * */
    public static Common.DateTime toProtoDateTime(LocalDateTime dateTime) {
        ValidationUtils.requireNotNull(dateTime, "DateTime (Дата, время)");

        if (dateTime.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Дата и время не могут быть в будущем.");
        }

        return Common.DateTime.newBuilder()
                .setDate(toProtoDate(dateTime))
                .setTime(toProtoTime(dateTime))
                .build();
    }

    /**
     * Метод для создания и возвращения структуры 'Date' в формате протокола
     * */
    private static Common.Date toProtoDate(LocalDateTime dateTime) {
        return Common.Date.newBuilder()
                .setYear(dateTime.getYear())
                .setMonth(dateTime.getMonthValue())
                .setDay(dateTime.getDayOfMonth())
                .build();
    }

    /**
     * Метод для создания и возвращения структуры 'Time' в формате протокола
     * */
    private static Common.Time toProtoTime(LocalDateTime dateTime) {
        return Common.Time.newBuilder()
                .setHour(dateTime.getHour())
                .setMinute(dateTime.getMinute())
                .setSecond(dateTime.getSecond())
                .build();
    }
}
