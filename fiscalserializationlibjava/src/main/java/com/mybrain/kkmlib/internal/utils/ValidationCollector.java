package com.mybrain.kkmlib.internal.utils;

import com.mybrain.kkmlib.api.errors.ErrorCode;
import com.mybrain.kkmlib.api.errors.KkmLibException;

import java.util.ArrayList;
import java.util.List;

public class ValidationCollector {

    private final List<ErrorCode> errors = new ArrayList<>();

    public void sweep(ErrorCode errorCode) {
        errors.add(errorCode);
    }

    public void sweep(ErrorCode errorCode, Object... args) {
        errors.add(new KkmLibException(errorCode, errorCode.format(args)).getCode());
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public List<ErrorCode> getErrors() {
        return List.copyOf(errors);
    }
}