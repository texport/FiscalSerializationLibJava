package com.mybrain.kkmlib.internal.factories.command.system;

import com.mybrain.kkmlib.api.request.CheckOfdConnectionRequest;
import com.mybrain.kkmlib.internal.factories.AbstractCommandFactoryRequest;
import com.mybrain.kkmlib.internal.models.MessageHeader;
import kz.kgdkkmproto.kkm.proto.Message;

public class CommandSystemFactoryRequest extends AbstractCommandFactoryRequest {

    public static byte[] createRequest(CheckOfdConnectionRequest input, long id, long token, int reqNum) {
        Message.Request payload = createPayloadRequest(input);
        byte[] payloadByte = createPayloadByte(payload);
        MessageHeader header = createHeader(id, token, reqNum, payloadByte.length);
        byte[] headerByte = createHeaderByte(header);
        return createRequestByte(headerByte, payloadByte);
    }

    private static Message.Request createPayloadRequest(CheckOfdConnectionRequest input) {
        return Message.Request.newBuilder()
                .setCommand(Message.CommandTypeEnum.COMMAND_SYSTEM)
                .build();
    }
}
