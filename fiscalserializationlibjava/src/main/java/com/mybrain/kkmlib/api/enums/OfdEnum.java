package com.mybrain.kkmlib.api.enums;

import com.mybrain.kkmlib.api.models.OfdEnvironment;

public enum OfdEnum {

    KAZAKHTELECOM(
            new OfdEnvironment("37.150.215.187", 7777),
            new OfdEnvironment("10.8.29.11", 7777)
    );

    private final OfdEnvironment test;
    private final OfdEnvironment production;

    OfdEnum(OfdEnvironment test, OfdEnvironment production) {
        this.test = test;
        this.production = production;
    }

    public OfdEnvironment test() {
        return test;
    }

    public OfdEnvironment production() {
        return production;
    }
}

