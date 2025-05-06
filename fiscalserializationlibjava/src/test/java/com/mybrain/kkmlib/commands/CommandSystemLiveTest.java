package com.mybrain.kkmlib.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.mybrain.kkmlib.api.DeliveryManager;
import com.mybrain.kkmlib.api.common.Kkm;
import com.mybrain.kkmlib.api.enums.OfdEnum;
import com.mybrain.kkmlib.api.models.reginfo.KkmRegInfo;
import com.mybrain.kkmlib.api.models.reginfo.OrgRegInfo;
import com.mybrain.kkmlib.api.request.CheckOfdConnectionRequest;
import com.mybrain.kkmlib.api.request.models.OfflinePeriodRequest;
import com.mybrain.kkmlib.api.request.models.RegInfoRequest;
import com.mybrain.kkmlib.api.request.models.ServiceRequest;
import com.mybrain.kkmlib.api.response.IResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class CommandSystemLiveTest {

    // Куда доставить
    private static final String OFD_IP = "37.150.215.187"; // IP ОФД
    private static final int OFD_PORT = 7777;               // Порт ОФД

    // Кому доставить
    private static final Kkm kkm = new Kkm(201873, 72696433, 1);

    @Test
    void shouldSendCommandSystemAndReceiveValidResponse() throws Exception {
        // 1. Сформировать запрос
        OfflinePeriodRequest offlinePeriodRequest = new OfflinePeriodRequest(LocalDateTime.now(), LocalDateTime.now());
        KkmRegInfo kkmRegInfo = new KkmRegInfo("391827192812", "5465434234", "201873");
        OrgRegInfo orgRegInfo = new OrgRegInfo("12312412f","123213214f","960624350642","123214214");
        RegInfoRequest regInfoRequest = new RegInfoRequest(kkmRegInfo, orgRegInfo);
        ServiceRequest serviceRequest = new ServiceRequest(offlinePeriodRequest, regInfoRequest);

        // Что доставить
        CheckOfdConnectionRequest checkOfdConnectionRequest = new CheckOfdConnectionRequest(serviceRequest);
        IResponse response = new DeliveryManager().deliver(checkOfdConnectionRequest, kkm, OfdEnum.KAZAKHTELECOM.test());

        System.out.println("Распечатываем полученный Response в виде библиотеки: " + response);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
        System.out.println("Распечатываем полученный Response в виде Json: " + json);
    }
}
