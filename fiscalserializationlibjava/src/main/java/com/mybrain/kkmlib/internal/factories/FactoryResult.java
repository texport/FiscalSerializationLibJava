package com.mybrain.kkmlib.internal.factories;

import com.mybrain.kkmlib.api.errors.ErrorCode;
import com.mybrain.kkmlib.api.errors.KkmLibException;

import java.util.List;
import java.util.stream.Collectors;

public class FactoryResult<T> {
    private final T result;
    private final List<ErrorCode> errors;

    private FactoryResult(T result, List<ErrorCode> errors) {
        this.result = result;
        this.errors = List.copyOf(errors);
    }

    public static <T> FactoryResult<T> success(T result) {
        return new FactoryResult<>(result, List.of());
    }

    public static <T> FactoryResult<T> failure(List<ErrorCode> errors) {
        return new FactoryResult<>(null, errors);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public T getResult() {
        return result;
    }

    public List<ErrorCode> getErrors() {
        return errors;
    }

    public void throwIfInvalid() {
        if (!isValid()) {
            String message = errors.stream()
                    .map(ErrorCode::message)
                    .map(msg -> "- " + msg)
                    .collect(Collectors.joining("\n"));
            throw new KkmLibException(ErrorCode.VALIDATION_FAILED, message);
        }
    }
}
