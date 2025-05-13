package com.mybrain.kkmlib.internal.utils;

import com.mybrain.kkmlib.api.errors.ErrorCode;
import com.mybrain.kkmlib.api.errors.KkmLibException;
import com.mybrain.kkmlib.api.utils.LoggerManager;

public class ValidationUtils {

    private ValidationUtils() { }

    public static boolean checkNotNull(Object value, String argumentName, ValidationCollector collector) {
        if (value == null) {
            collector.sweep(ErrorCode.ARGUMENT_NULL, argumentName);
            return false;
        }
        return true;
    }

    public static boolean checkHasField(boolean hasField, String argumentName, ErrorCode errorCode, ValidationCollector collector) {
        if (!hasField) {
            collector.sweep(errorCode, argumentName);
            return false;
        }
        return true;
    }

    public static boolean checkStringLength(String value, int length, String argumentName, ValidationCollector collector) {
        if (value.length() != length) {
            collector.sweep(ErrorCode.ARGUMENT_EQUAL_LENGTH, length, argumentName);
            return false;
        }
        return true;
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
