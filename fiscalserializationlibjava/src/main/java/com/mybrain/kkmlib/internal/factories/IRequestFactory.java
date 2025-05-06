package com.mybrain.kkmlib.internal.factories;

import com.mybrain.kkmlib.api.common.Kkm;
import com.mybrain.kkmlib.api.request.IRequest;

@FunctionalInterface
public interface IRequestFactory<T extends IRequest> {
    byte[] createRequest(T request, Kkm kkm);
}
