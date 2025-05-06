package com.mybrain.kkmlib.api.models;

public class OfdEnvironment {
    private final String ip;
    private final int port;

    public OfdEnvironment(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}
