package com.mybrain.kkmlib.internal.factories;

import com.mybrain.kkmlib.api.errors.ErrorCode;
import com.mybrain.kkmlib.api.errors.KkmLibException;
import com.mybrain.kkmlib.internal.models.ResponseParts;

import java.nio.ByteBuffer;

public class CommonFactory {

    public static byte[] createCommandRequest(byte[] headerByte, byte[] payloadByte) {
        return ByteBuffer.allocate(headerByte.length + payloadByte.length)
                .put(headerByte)
                .put(payloadByte)
                .array();
    }

    public static ResponseParts createCommandResponse(byte[] response) {
        final int HEADER_SIZE = 18;

        if (response == null || response.length < HEADER_SIZE) {
            throw new KkmLibException(ErrorCode.INVALID_HEADER_SIZE);
        }

        byte[] header = new byte[HEADER_SIZE];
        byte[] payload = new byte[response.length - HEADER_SIZE];

        // FIXME: Нужно проверить будет ли тут баг с нумерацией массива.
        // FIXME: Бага не будет потом что берется 18 элементов от 0 до 17, payload берется с 18 и до конца
        System.arraycopy(response, 0, header, 0, HEADER_SIZE);
        System.arraycopy(response, HEADER_SIZE, payload, 0, payload.length);

        return new ResponseParts(header, payload);
    }
}
