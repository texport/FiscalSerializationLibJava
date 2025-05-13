package com.mybrain.kkmlib.internal;

import com.mybrain.kkmlib.api.common.Kkm;
import com.mybrain.kkmlib.api.errors.ErrorCode;
import com.mybrain.kkmlib.api.errors.KkmLibException;
import com.mybrain.kkmlib.api.models.OfdEnvironment;
import com.mybrain.kkmlib.api.request.IRequest;
import com.mybrain.kkmlib.api.response.IResponse;

public class InternalDeliveryExecutor {

    private final RequestHandlerRegistry registry = new RequestHandlerRegistry();

    public <T extends IRequest, R extends IResponse> R deliverInternal(T request, Kkm kkm, OfdEnvironment ofd) {
        @SuppressWarnings("unchecked")
        IRequestHandler<T> handler = (IRequestHandler<T>) registry.getHandler(request.getClass());

        if (handler == null) {
            throw new KkmLibException(ErrorCode.HANDLER_NOT_FOUND, "Обработчик не найден для запроса: " + request.getClass().getSimpleName());
        }

        return (R) handler.handle(request, kkm, ofd);
    }
}
