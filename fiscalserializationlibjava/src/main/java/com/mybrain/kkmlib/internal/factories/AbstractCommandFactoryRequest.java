package com.mybrain.kkmlib.internal.factories;

import com.mybrain.kkmlib.internal.MessageHeaderCodec;
import com.mybrain.kkmlib.internal.models.MessageHeader;
import kz.kgdkkmproto.kkm.proto.Message;

public abstract class AbstractCommandFactoryRequest {

    protected static byte[] createPayloadByte(Message.Request request) {
        return request.toByteArray();
    }

    protected static MessageHeader createHeader(long id, long token, int reqNum, int payloadSize) {
        return MessageHeaderFactory.create(id, token, reqNum, payloadSize);
    }

    protected static byte[] createHeaderByte(MessageHeader header) {
        return MessageHeaderCodec.encode(header);
    }

    protected static byte[] createRequestByte(byte[] headerByte, byte[] payloadByte) {
        return CommonFactory.createCommandRequest(headerByte, payloadByte);
    }
}
