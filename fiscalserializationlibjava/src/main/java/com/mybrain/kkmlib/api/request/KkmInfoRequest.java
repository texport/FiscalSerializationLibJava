package com.mybrain.kkmlib.api.request;

import com.mybrain.kkmlib.api.request.models.ServiceRequest;

public record KkmInfoRequest(ServiceRequest serviceRequest) implements IRequest { }