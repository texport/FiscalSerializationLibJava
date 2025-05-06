package com.mybrain.kkmlib.internal.network;

import com.mybrain.kkmlib.internal.MessageHeaderCodec;
import com.mybrain.kkmlib.internal.models.MessageHeader;

import java.io.*;
import java.net.*;

public class SingleChannelNetworkClient implements INetworkClient {

    private static final int TIMEOUT_MILLIS = 7000;
    private static final int HEADER_SIZE = 18;

    private static final SingleChannelNetworkClient instance = new SingleChannelNetworkClient();

    private SingleChannelNetworkClient() { }

    public static SingleChannelNetworkClient getInstance() {
        return instance;
    }

    public byte[] sendToServer(byte[] message, String serverIP, int serverPort) throws IOException {
        Socket socket = createSocket();
        try {
            setTimeouts(socket);
            connect(socket, serverIP, serverPort);
            sendMessage(socket, message);
            return receiveMessage(socket);
        } finally {
            closeSocket(socket);
        }
    }

    private Socket createSocket() throws IOException {
        return new Socket();
    }

    private void setTimeouts(Socket socket) throws SocketException {
        socket.setSoTimeout(TIMEOUT_MILLIS);
    }

    private void connect(Socket socket, String ip, int port) throws IOException {
        socket.connect(new InetSocketAddress(ip, port), TIMEOUT_MILLIS);
    }

    private void sendMessage(Socket socket, byte[] message) throws IOException {
        OutputStream out = socket.getOutputStream();
        out.write(message);
        out.flush();
    }

    private byte[] receiveMessage(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();

        byte[] headerBytes = readExactBytes(in, HEADER_SIZE);
        if (headerBytes.length < HEADER_SIZE) {
            throw new IOException("Недостаточно данных для заголовка");
        }

        MessageHeader header = MessageHeaderCodec.decode(headerBytes);
        long totalSize = header.size();
        long payloadSize = totalSize - HEADER_SIZE;

        if (payloadSize > Integer.MAX_VALUE) {
            throw new IOException("Размер сообщения превышает допустимый лимит");
        }

        byte[] payloadBytes = readExactBytes(in, (int) payloadSize);
        if (payloadBytes.length < payloadSize) {
            throw new IOException("Недостаточно данных для тела сообщения");
        }

        ByteArrayOutputStream result = new ByteArrayOutputStream((int) totalSize);
        result.write(headerBytes);
        result.write(payloadBytes);

        return result.toByteArray();
    }

    private byte[] readExactBytes(InputStream in, int count) throws IOException {
        byte[] buffer = new byte[count];
        int bytesRead = 0;
        while (bytesRead < count) {
            int read = in.read(buffer, bytesRead, count - bytesRead);
            if (read == -1) break;
            bytesRead += read;
        }
        if (bytesRead < count) {
            byte[] partial = new byte[bytesRead];
            System.arraycopy(buffer, 0, partial, 0, bytesRead);
            return partial;
        }
        return buffer;
    }

    private void closeSocket(Socket socket) {
        try {
            socket.close();
        } catch (IOException e) {
            // логировать можно здесь, если нужно
        }
    }
}
