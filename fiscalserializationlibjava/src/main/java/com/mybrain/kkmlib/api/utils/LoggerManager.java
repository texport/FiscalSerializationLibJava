package com.mybrain.kkmlib.api.utils;

import com.mybrain.kkmlib.api.enums.LogLevel;
import com.mybrain.kkmlib.api.errors.ErrorCode;

public class LoggerManager {

    private static IKkmLibLogger logger;
    private static LogLevel level = LogLevel.INFO;

    public static void setLogger(IKkmLibLogger externalLogger) {
        logger = externalLogger;
    }

    public static void setLogLevel(LogLevel newLevel) {
        level = newLevel;
    }

    public static void info(String message) {
        if (logger != null && level.ordinal() >= LogLevel.INFO.ordinal()) {
            logger.info(message);
        }
    }

    public static void error(String message, ErrorCode code, Object... args) {
        if (logger != null && level.ordinal() >= LogLevel.ERROR.ordinal()) {
            logger.error(message, code, args);
        }
    }

    public static void debug(String message) {
        if (logger != null && level.ordinal() >= LogLevel.DEBUG.ordinal()) {
            logger.debug(message);
        }
    }

    public static boolean isEnabled() {
        return logger != null;
    }

    public static LogLevel getLogLevel() {
        return level;
    }
}