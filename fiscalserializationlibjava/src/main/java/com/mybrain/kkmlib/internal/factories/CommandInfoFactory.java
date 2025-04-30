package com.mybrain.kkmlib.internal.factories;

import com.mybrain.kkmlib.api.request.KkmInfoRequest;
import com.mybrain.kkmlib.internal.models.MessageHeader;
import kz.kgdkkmproto.kkm.proto.Message;

public class CommandInfoFactory extends AbstractCommandFactory {

    /**
     * Метод для создания и возвращения байтового массива header + payload
     * */
    public static byte[] createRequest(KkmInfoRequest input, long id, long token, int reqNum) {
        Message.Request payload = createPayload(input);
        byte[] payloadByte = createPayloadByte(payload);
        MessageHeader header = createHeader(id, token, reqNum, payloadByte.length);
        byte[] headerByte = createHeaderByte(header);
        return createRequestByte(headerByte, payloadByte);
    }

    /**
     * Метод создания payload для COMMAND_INFO
     * */
    private static Message.Request createPayload(KkmInfoRequest input) {
        return Message.Request.newBuilder()
                .setCommand(Message.CommandTypeEnum.COMMAND_INFO)
                // TODO: добавить сервисную часть
                .build();
    }
}
