package com.mybrain.kkmlib.api.models.ticketad;

import com.mybrain.kkmlib.api.enums.TicketAdType;

public record TicketAdInfo(TicketAdType type, long version) { }
