package com.mybrain.kkmlib.api.utils;

import com.mybrain.kkmlib.api.errors.ErrorCode;

public interface IKkmLibLogger {
    void info(String message);
    void warn(String message);
    void error(String message, ErrorCode code, Object... args);
    void debug(String message);
}
