package com.mybrain.kkmlib.internal.factories.command.info;

import com.mybrain.kkmlib.api.common.Kkm;
import com.mybrain.kkmlib.api.request.KkmInfoRequest;
import com.mybrain.kkmlib.internal.factories.AbstractCommandFactoryRequest;
import com.mybrain.kkmlib.internal.factories.ServiceFactoryRequest;
import com.mybrain.kkmlib.internal.models.MessageHeader;
import kz.kgdkkmproto.kkm.proto.Message;

public class CommandInfoFactoryRequest extends AbstractCommandFactoryRequest<KkmInfoRequest> {

    /**
     * Метод для создания и возвращения байтового массива header + payload
     * */
    @Override
    public byte[] createRequest(KkmInfoRequest input, Kkm kkm) {
        Message.Request payload = createPayload(input);
        byte[] payloadByte = createPayloadByte(payload);
        MessageHeader header = createHeader(kkm.id(), kkm.token(), kkm.reqNum(), payloadByte.length);
        byte[] headerByte = createHeaderByte(header);
        return createRequestByte(headerByte, payloadByte);
    }

    /**
     * Метод создания payload для COMMAND_INFO
     * */
    //зачем тут в аргументах KkmInfoRequest?
    private static Message.Request createPayload(KkmInfoRequest input) {
        return Message.Request.newBuilder()
                .setCommand(Message.CommandTypeEnum.COMMAND_INFO)
                .setService(ServiceFactoryRequest.createRequest(input.serviceRequest()))
                .build();
    }
}
