package com.mybrain.kkmlib.api;

import com.mybrain.kkmlib.api.common.Kkm;
import com.mybrain.kkmlib.api.models.OfdEnvironment;
import com.mybrain.kkmlib.api.request.IRequest;
import com.mybrain.kkmlib.api.response.IResponse;
import com.mybrain.kkmlib.internal.InternalDeliveryExecutor;

public class DeliveryManager {
    private final InternalDeliveryExecutor executor;

    public DeliveryManager() {
        this.executor = new InternalDeliveryExecutor();
    }

    public IResponse deliver(IRequest request, Kkm kkm, OfdEnvironment ofd) {
        return executor.deliverInternal(request, kkm, ofd);
    }
}
