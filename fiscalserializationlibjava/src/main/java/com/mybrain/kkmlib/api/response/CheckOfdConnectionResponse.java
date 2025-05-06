package com.mybrain.kkmlib.api.response;

import com.mybrain.kkmlib.api.common.Kkm;
import com.mybrain.kkmlib.api.common.Result;
import com.mybrain.kkmlib.api.response.models.ServiceResponse;

import java.util.Optional;

public record CheckOfdConnectionResponse(Kkm kkm, Result result, Optional<ServiceResponse> service) implements IResponse { }
