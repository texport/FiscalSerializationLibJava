package com.mybrain.kkmlib.internal.utils;

import com.mybrain.kkmlib.api.errors.ErrorCode;
import com.mybrain.kkmlib.api.errors.KkmLibException;

public class ValidationUtils {
    private ValidationUtils() { }

    public static <T> void requireNotNull(T value, String argumentName) {
        if (value == null) {
            throw new KkmLibException(ErrorCode.ARGUMENT_NULL, argumentName);
        }
    }

    public static void requireStringLength(String value, int length, String argumentName) {
        if (value.length() != length) {
            throw new KkmLibException(ErrorCode.ARGUMENT_EQUAL_LENGTH, length, argumentName);
        }
    }

    public static void requireStringNumber(String value, String argumentName) {
        if (!value.matches("^\\d+$")) {
            throw new KkmLibException(ErrorCode.ARGUMENT_STRING_NUMBER, argumentName);
        }
    }

    public static void requireSpacesBeginEnd(String value, String argumentName) {
        if (!value.equals(value.trim())) {
            throw new KkmLibException(ErrorCode.ARGUMENT_SPACES_BEGIN_END, argumentName);
        }
    }

    public static void requireNotEmpty(String value, String argumentName) {
        if (value.isEmpty()) {
            throw new KkmLibException(ErrorCode.ARGUMENT_EMPTY, argumentName);
        }
    }

    public static void requireNotLongerCould(String value, int length, String argumentName) {
        if (value.length() > length) {
            throw new KkmLibException(ErrorCode.ARGUMENT_NOT_LONGER_COULD, length, argumentName);
        }
    }

    public static void requireNotSpaces(String value, String argumentName) {
        if (value.matches("^ +$")) {
            throw new KkmLibException(ErrorCode.ARGUMENT_NOT_SPACES, argumentName);
        }
    }
}
