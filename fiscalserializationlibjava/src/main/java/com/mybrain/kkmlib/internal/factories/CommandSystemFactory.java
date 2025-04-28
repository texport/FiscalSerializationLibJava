package com.mybrain.kkmlib.internal.factories;

import com.mybrain.kkmlib.api.request.CheckOfdConnection;
import com.mybrain.kkmlib.internal.models.MessageHeader;
import kz.kgdkkmproto.kkm.proto.Message;

public class CommandSystemFactory extends AbstractCommandFactory {

    public static byte[] createRequest(CheckOfdConnection input, long id, long token, int reqNum, int payloadSize) {
        Message.Request payload = createPayload(input);
        MessageHeader header = createHeader(id, token, reqNum, payloadSize);
        byte[] payloadByte = createPayloadByte(payload);
        byte[] headerByte = createHeaderByte(header);
        return createRequestByte(headerByte, payloadByte);
    }

    private static Message.Request createPayload(CheckOfdConnection input) {
        return Message.Request.newBuilder()
                .setCommand(Message.CommandTypeEnum.COMMAND_SYSTEM)
                .build();
    }
}
