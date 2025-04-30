package com.mybrain.kkmlib.internal.mappers;

import com.mybrain.kkmlib.api.enums.TicketAdType;
import com.mybrain.kkmlib.api.errors.ErrorCode;
import com.mybrain.kkmlib.api.errors.KkmLibException;

import static kz.kgdkkmproto.kkm.proto.Common.TicketAdTypeEnum;

import java.util.HashMap;
import java.util.Map;

public final class TicketAdTypeMapper {

    private TicketAdTypeMapper() {}

    private static final Map<TicketAdTypeEnum, TicketAdType> PROTO_TO_USER = new HashMap<>();
    private static final Map<TicketAdType, TicketAdTypeEnum> USER_TO_PROTO = new HashMap<>();

    static {
        map(TicketAdType.TICKET_AD_OFD, TicketAdTypeEnum.TICKET_AD_OFD);
        map(TicketAdType.TICKET_AD_ORG, TicketAdTypeEnum.TICKET_AD_ORG);
        map(TicketAdType.TICKET_AD_POS, TicketAdTypeEnum.TICKET_AD_POS);
        map(TicketAdType.TICKET_AD_KKM, TicketAdTypeEnum.TICKET_AD_KKM);
        map(TicketAdType.TICKET_AD_INFO, TicketAdTypeEnum.TICKET_AD_INFO);
    }

    private static void map(TicketAdType user, TicketAdTypeEnum proto) {
        USER_TO_PROTO.put(user, proto);
        PROTO_TO_USER.put(proto, user);
    }

    /**
     * Преобразовать proto-тип в пользовательский enum.
     * @throws KkmLibException если значение неизвестно
     */
    public static TicketAdType fromProto(TicketAdTypeEnum protoEnum) {
        TicketAdType result = PROTO_TO_USER.get(protoEnum);
        if (result == null) {
            throw new KkmLibException(ErrorCode.UNKNOWN_OFD_TICKET_AD_TYPE);
        }
        return result;
    }

    /**
     * Преобразовать пользовательский enum в proto.
     * @throws KkmLibException если значение неизвестно
     */
    public static TicketAdTypeEnum toProto(TicketAdType userEnum) {
        TicketAdTypeEnum result = USER_TO_PROTO.get(userEnum);
        if (result == null) {
            throw new KkmLibException(ErrorCode.UNKNOWN_TICKET_AD_TYPE);
        }
        return result;
    }

    /**
     * Преобразовать числовой код proto в пользовательский enum.
     */
    public static TicketAdType fromProtoCode(int code) {
        return fromProto(TicketAdTypeEnum.forNumber(code));
    }

    /**
     * Получить числовой код proto по-пользовательскому enum.
     */
    public static int toProtoCode(TicketAdType userEnum) {
        return toProto(userEnum).getNumber();
    }
}
