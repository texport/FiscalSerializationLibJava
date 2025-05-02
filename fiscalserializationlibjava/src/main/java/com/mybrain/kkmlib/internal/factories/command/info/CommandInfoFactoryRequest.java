package com.mybrain.kkmlib.internal.factories.command.info;

import com.mybrain.kkmlib.api.request.KkmInfoRequest;
import com.mybrain.kkmlib.api.request.models.ServiceRequest;
import com.mybrain.kkmlib.internal.factories.AbstractCommandFactoryRequest;
import com.mybrain.kkmlib.internal.factories.ServiceFactoryRequest;
import com.mybrain.kkmlib.internal.models.MessageHeader;
import kz.kgdkkmproto.kkm.proto.Message;

public class CommandInfoFactoryRequest extends AbstractCommandFactoryRequest {

    /**
     * Метод для создания и возвращения байтового массива header + payload
     * */
    public static byte[] createRequest(KkmInfoRequest input, ServiceRequest service, long id, long token, int reqNum) {
        Message.Request payload = createPayload(input, service);
        byte[] payloadByte = createPayloadByte(payload);
        MessageHeader header = createHeader(id, token, reqNum, payloadByte.length);
        byte[] headerByte = createHeaderByte(header);
        return createRequestByte(headerByte, payloadByte);
    }

    /**
     * Метод создания payload для COMMAND_INFO
     * */
    //зачем тут в аргументах KkmInfoRequest?
    private static Message.Request createPayload(KkmInfoRequest input, ServiceRequest service) {
        return Message.Request.newBuilder()
                .setCommand(Message.CommandTypeEnum.COMMAND_INFO)
                .setService(ServiceFactoryRequest.createRequest(service))
                .build();
    }
}
