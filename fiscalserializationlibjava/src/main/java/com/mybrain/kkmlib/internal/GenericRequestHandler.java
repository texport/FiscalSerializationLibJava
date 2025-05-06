package com.mybrain.kkmlib.internal;

import com.mybrain.kkmlib.api.common.Kkm;
import com.mybrain.kkmlib.api.errors.ErrorCode;
import com.mybrain.kkmlib.api.errors.KkmLibException;
import com.mybrain.kkmlib.api.models.OfdEnvironment;
import com.mybrain.kkmlib.api.request.IRequest;
import com.mybrain.kkmlib.api.response.IResponse;
import com.mybrain.kkmlib.internal.factories.IRequestFactory;
import com.mybrain.kkmlib.internal.factories.IResponseFactory;
import com.mybrain.kkmlib.internal.network.INetworkClient;

import java.io.IOException;

public class GenericRequestHandler<T extends IRequest, R extends IResponse> implements IRequestHandler<T> {
    private final IRequestFactory<T> requestFactory;
    private final IResponseFactory<R> responseFactory;
    private final INetworkClient networkClient;

    public GenericRequestHandler(
            IRequestFactory<T> requestFactory,
            IResponseFactory<R> responseFactory,
            INetworkClient networkClient
    ) {
        this.requestFactory = requestFactory;
        this.responseFactory = responseFactory;
        this.networkClient = networkClient;
    }

    @Override
    public IResponse handle(T request, Kkm kkm, OfdEnvironment ofd) {
        try {
            byte[] requestBytes = requestFactory.createRequest(request, kkm);
            byte[] responseBytes = networkClient.sendToServer(requestBytes, ofd.getIp(), ofd.getPort());
            return responseFactory.getResponse(responseBytes);
        } catch (IOException e) {
            throw new KkmLibException(ErrorCode.NETWORK_IO_ERROR, e);
        } catch (Exception e) {
            throw new KkmLibException(ErrorCode.UNEXPECTED_ERROR, e);
        }
    }
}
