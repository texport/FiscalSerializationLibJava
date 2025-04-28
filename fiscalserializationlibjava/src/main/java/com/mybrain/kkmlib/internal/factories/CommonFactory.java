package com.mybrain.kkmlib.internal.factories;

import java.nio.ByteBuffer;

public class CommonFactory {
    // Создает запрос в виде byte[] с готовым заголовком и payload
    public static byte[] createCommandRequest(byte[] headerByte, byte[] payloadByte) {
        return ByteBuffer.allocate(headerByte.length + payloadByte.length)
                .put(headerByte)
                .put(payloadByte)
                .array();
    }
}
