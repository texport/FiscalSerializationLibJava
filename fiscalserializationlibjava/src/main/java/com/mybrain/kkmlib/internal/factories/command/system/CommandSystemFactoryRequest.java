package com.mybrain.kkmlib.internal.factories.command.system;

import com.mybrain.kkmlib.api.common.Kkm;
import com.mybrain.kkmlib.api.request.CheckOfdConnectionRequest;
import com.mybrain.kkmlib.internal.factories.AbstractCommandFactoryRequest;
import com.mybrain.kkmlib.internal.factories.ServiceFactoryRequest;
import com.mybrain.kkmlib.internal.models.MessageHeader;
import kz.kgdkkmproto.kkm.proto.Message;

public class CommandSystemFactoryRequest extends AbstractCommandFactoryRequest<CheckOfdConnectionRequest> {

    @Override
    public byte[] createRequest(CheckOfdConnectionRequest input, Kkm kkm) {
        Message.Request payload = createPayloadRequest(input);
        byte[] payloadByte = createPayloadByte(payload);
        MessageHeader header = createHeader(kkm.id(), kkm.token(), kkm.reqNum(), payloadByte.length);
        byte[] headerByte = createHeaderByte(header);
        return createRequestByte(headerByte, payloadByte);
    }

    private static Message.Request createPayloadRequest(CheckOfdConnectionRequest input) {
        return Message.Request.newBuilder()
                .setCommand(Message.CommandTypeEnum.COMMAND_SYSTEM)
                .setService(ServiceFactoryRequest.createRequest(input.serviceRequest()))
                .build();
    }
}
