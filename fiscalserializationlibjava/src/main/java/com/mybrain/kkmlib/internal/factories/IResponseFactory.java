package com.mybrain.kkmlib.internal.factories;

import com.mybrain.kkmlib.api.response.IResponse;

@FunctionalInterface
public interface IResponseFactory<R extends IResponse> {
    R getResponse(byte[] response) throws Exception;
}
