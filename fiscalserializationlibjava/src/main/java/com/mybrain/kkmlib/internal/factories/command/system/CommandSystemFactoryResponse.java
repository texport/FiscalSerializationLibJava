package com.mybrain.kkmlib.internal.factories.command.system;

import com.google.protobuf.InvalidProtocolBufferException;
import com.mybrain.kkmlib.api.common.Kkm;
import com.mybrain.kkmlib.api.common.Result;
import com.mybrain.kkmlib.api.errors.ErrorCode;
import com.mybrain.kkmlib.api.errors.KkmLibException;
import com.mybrain.kkmlib.api.response.CheckOfdConnectionResponse;
import com.mybrain.kkmlib.internal.factories.AbstractCommandFactoryResponse;
import com.mybrain.kkmlib.internal.factories.CommonFactory;
import com.mybrain.kkmlib.internal.models.MessageHeader;
import com.mybrain.kkmlib.internal.models.ResponseParts;
import kz.kgdkkmproto.kkm.proto.Message;

public class CommandSystemFactoryResponse extends AbstractCommandFactoryResponse {

    public static CheckOfdConnectionResponse getResponse(byte[] response) throws InvalidProtocolBufferException {
        ResponseParts headerPayloadByte = CommonFactory.createCommandResponse(response);
        MessageHeader header = createByteToHeader(headerPayloadByte.headerByte());
        Message.Response payload = createByteToPayload(headerPayloadByte.payloadByte());

        return createCheckOfdConnectionResponse(header, payload);
    }

    private static CheckOfdConnectionResponse createCheckOfdConnectionResponse(MessageHeader header, Message.Response payload) {
        Kkm kkm = new Kkm(header.id(), header.token(), header.reqNum());
        Message.CommandTypeEnum command = extractCommand(payload);

        if (command.getNumber() != 0) {
            throw new KkmLibException(ErrorCode.PAYLOAD_BAD_COMMAND);
        }

        Message.Result resultPayload = payload.getResult();
        Result result = new Result(resultPayload.getResultCode(), resultPayload.getResultText());

        // TODO: Добавить сервисную часть после фабрики

        return new CheckOfdConnectionResponse(kkm, result, null);
    }


}
