package com.mybrain.kkmlib.internal;

import com.mybrain.kkmlib.api.errors.ErrorCode;
import com.mybrain.kkmlib.api.errors.KkmLibException;
import com.mybrain.kkmlib.internal.models.MessageHeader;
import com.mybrain.kkmlib.internal.factories.MessageHeaderFactory;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MessageHeaderCodec {

    public static byte[] encode(MessageHeader header) {
        ByteBuffer buffer = ByteBuffer.allocate(MessageHeaderFactory.getHeaderSize());
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        buffer.putShort((short) header.appCode());
        buffer.putShort((short) header.version());
        buffer.putInt((int) header.size());
        buffer.putInt((int) header.id());
        buffer.putInt((int) header.token());
        buffer.putShort((short) header.reqNum());

        return buffer.array();
    }

    public static MessageHeader decode(byte[] data) {
        if (data.length < MessageHeaderFactory.getHeaderSize()) {
            throw new KkmLibException(ErrorCode.INVALID_HEADER_SIZE);
        }

        ByteBuffer buffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);

        int appCode = Short.toUnsignedInt(buffer.getShort());
        int version = Short.toUnsignedInt(buffer.getShort());
        long size = Integer.toUnsignedLong(buffer.getInt());
        long id = Integer.toUnsignedLong(buffer.getInt());
        long token = Integer.toUnsignedLong(buffer.getInt());
        int reqNum = Short.toUnsignedInt(buffer.getShort());

        return new MessageHeader(appCode, version, size, id, token, reqNum);
    }
}