package com.mybrain.kkmlib.internal;

import com.mybrain.kkmlib.api.common.Kkm;
import com.mybrain.kkmlib.api.enums.OfdEnum;
import com.mybrain.kkmlib.api.models.OfdEnvironment;
import com.mybrain.kkmlib.api.request.IRequest;
import com.mybrain.kkmlib.api.response.IResponse;

public interface IRequestHandler<T extends IRequest> {
    IResponse handle(T request, Kkm kkm, OfdEnvironment ofd);
}
