package com.mybrain.kkmlib.internal.factories;

import com.google.protobuf.InvalidProtocolBufferException;
import com.mybrain.kkmlib.api.errors.ErrorCode;
import com.mybrain.kkmlib.api.errors.KkmLibException;
import com.mybrain.kkmlib.internal.MessageHeaderCodec;
import com.mybrain.kkmlib.internal.models.MessageHeader;
import com.mybrain.kkmlib.api.common.Result;
import kz.kgdkkmproto.kkm.proto.Message;

public abstract class AbstractCommandFactoryResponse {

    protected static MessageHeader createByteToHeader(byte[] messageHeader) {
        return MessageHeaderCodec.decode(messageHeader);
    }

    protected static Message.Response createByteToPayload(byte[] payloadByte) throws InvalidProtocolBufferException {
        return Message.Response.parseFrom(payloadByte);
    }

    protected static Message.CommandTypeEnum extractCommand(Message.Response responsePayload) {
        if (!responsePayload.hasCommand()) {
            throw new KkmLibException(ErrorCode.PAYLOAD_NOT_COMMAND);
        }

        return responsePayload.getCommand();
    }

    protected static Result createPayloadResult(Message.Response responsePayload) {
        if (!responsePayload.hasResult()) {
            throw new KkmLibException(ErrorCode.PAYLOAD_NOT_RESULT);
        }

        return new Result(responsePayload.getResult().getResultCode(), responsePayload.getResult().getResultText());
    }
}
