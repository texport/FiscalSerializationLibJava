package com.mybrain.kkmlib.internal;

import com.mybrain.kkmlib.api.request.CheckOfdConnectionRequest;
import com.mybrain.kkmlib.api.request.IRequest;
import com.mybrain.kkmlib.api.response.IResponse;
import com.mybrain.kkmlib.internal.factories.IRequestFactory;
import com.mybrain.kkmlib.internal.factories.IResponseFactory;
import com.mybrain.kkmlib.internal.factories.command.system.CommandSystemFactoryRequest;
import com.mybrain.kkmlib.internal.factories.command.system.CommandSystemFactoryResponse;
import com.mybrain.kkmlib.internal.network.SingleChannelNetworkClient;

import java.util.HashMap;
import java.util.Map;

public class RequestHandlerRegistry {

    private final Map<Class<? extends IRequest>, IRequestHandler<? extends IRequest>> handlers = new HashMap<>();

    public RequestHandlerRegistry() {
        // Регистрируем обработчики
        register(
                CheckOfdConnectionRequest.class,
                new CommandSystemFactoryRequest(),
                new CommandSystemFactoryResponse()
        );
    }

    public <T extends IRequest, R extends IResponse> void register(
            Class<T> requestClass,
            IRequestFactory<T> requestFactory,
            IResponseFactory<R> responseFactory
    ) {
        handlers.put(
                requestClass,
                new GenericRequestHandler<>(requestFactory, responseFactory, SingleChannelNetworkClient.getInstance())
        );
    }

    @SuppressWarnings("unchecked")
    public <T extends IRequest> IRequestHandler<T> getHandler(Class<T> requestClass) {
        return (IRequestHandler<T>) handlers.get(requestClass);
    }
}