package com.mybrain.kkmlib.api.errors;

public class KkmLibException extends RuntimeException {
    private final ErrorCode code;

    public KkmLibException(ErrorCode code) {
        super(code.message());
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }
}
