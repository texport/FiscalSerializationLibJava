package com.mybrain.kkmlib.commands;

import com.mybrain.kkmlib.api.common.Kkm;
import com.mybrain.kkmlib.api.request.CheckOfdConnectionRequest;
import com.mybrain.kkmlib.api.response.CheckOfdConnectionResponse;
import com.mybrain.kkmlib.internal.MessageHeaderCodec;
import com.mybrain.kkmlib.internal.factories.command.system.CommandSystemFactoryRequest;
import com.mybrain.kkmlib.internal.factories.command.system.CommandSystemFactoryResponse;
import com.mybrain.kkmlib.internal.models.MessageHeader;
import com.mybrain.kkmlib.internal.network.SingleChannelNetworkClient;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandSystemLiveTest {

    private static final String OFD_IP = "37.150.215.187"; // IP ОФД
    private static final int OFD_PORT = 7777;               // Порт ОФД
    private static final Kkm kkm = new Kkm(201873, 72696433, 1);

    @Test
    void shouldSendCommandSystemAndReceiveValidResponse() throws Exception {
        // 1. Сформировать запрос
        CheckOfdConnectionRequest checkOfdConnectionRequest = new CheckOfdConnectionRequest();
        byte[] request = CommandSystemFactoryRequest.createRequest(checkOfdConnectionRequest, kkm);
        System.out.println("Распечатываем собранный Request: " + Arrays.toString(request));

        // 2. Отправить и получить ответ
        byte[] response = SingleChannelNetworkClient.getInstance()
                .sendToServer(request, OFD_IP, OFD_PORT);
        System.out.println("Распечатываем полученный Response в байтах: " + Arrays.toString(response));

        CheckOfdConnectionResponse commandSystemFactoryResponse = CommandSystemFactoryResponse.getResponse(response);
        System.out.println("Распечатываем полученный Response в виде библиотеки: " + commandSystemFactoryResponse);
        
        // 3. Проверки
        assertNotNull(response);
        assertTrue(response.length >= 18, "Ответ должен содержать минимум заголовок");

        byte[] headerBytes = new byte[18];
        System.arraycopy(response, 0, headerBytes, 0, 18);

        MessageHeader header = MessageHeaderCodec.decode(headerBytes);
        assertTrue(header.size() <= response.length, "Размер в заголовке больше, чем фактический размер");

        System.out.println("Ответ получен: " + header);
    }
}
