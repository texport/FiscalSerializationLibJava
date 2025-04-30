package com.mybrain.kkmlib.api.response.models;

import com.mybrain.kkmlib.api.models.ticketad.TicketAd;

import java.util.List;
import java.util.Optional;

public record ServiceResponse(Optional<List<TicketAd>> ticketAds, RegInfoResponse regInfo) { }
