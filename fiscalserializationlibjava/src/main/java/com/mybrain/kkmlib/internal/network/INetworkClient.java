package com.mybrain.kkmlib.internal.network;

import java.io.IOException;

public interface INetworkClient {
    byte[] sendToServer(byte[] message, String ip, int port) throws IOException;
}
