package com.mybrain.kkmlib.api.enums;

public enum TicketAdType {
    TICKET_AD_OFD(0),
    TICKET_AD_ORG(1),
    TICKET_AD_POS(2),
    TICKET_AD_KKM(3),
    TICKET_AD_INFO(4);

    private final int code;

    TicketAdType(int code) { this.code = code; }

    public int getCode() { return code; }

    public static TicketAdType fromCode(int code) {
        for (TicketAdType type : values()) {
            if (type.code == code) return type;
        }
        return null;
    }
}
