package com.mybrain.kkmlib.internal.factories;

import com.mybrain.kkmlib.internal.models.MessageHeader;

public class MessageHeaderFactory {
    private static final int APP_CODE = 0x81A2;
    private static final int PROTOCOL_VERSION = 202;
    private static final int HEADER_SIZE = 18;

    public static MessageHeader create(long id, long token, int reqNum, int payloadSize) {
        long totalSize = payloadSize + HEADER_SIZE;
        return new MessageHeader(APP_CODE, PROTOCOL_VERSION, totalSize, id, token, reqNum);
    }

    public static int getHeaderSize() {
        return HEADER_SIZE;
    }
}
