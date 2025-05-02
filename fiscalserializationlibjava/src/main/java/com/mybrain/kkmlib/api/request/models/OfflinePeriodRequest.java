package com.mybrain.kkmlib.api.request.models;

import java.time.LocalDateTime;

public record OfflinePeriodRequest(LocalDateTime beginDateTime, LocalDateTime endDateTime) { }