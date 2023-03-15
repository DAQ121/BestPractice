package com.daiaoqi.middleware.websocket;

public enum WebServerEnum {

    server;

    private static MsgWebSocketServer socketServer = null;

    public static void init(MsgWebSocketServer server) {
        socketServer = server;
        if (socketServer != null) {
            socketServer.start();
        }
    }
}
