package com.daiaoqi.middleware.websocket;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Iterator;


/**
 * websocket服务器
 */
public class MsgWebSocketServer extends WebSocketServer{

    public MsgWebSocketServer(int port) {
        super(new InetSocketAddress(port));
    }
    /**
     * WebSocket连接关闭时调用
     */
    @Override
    public void onClose(WebSocket ws, int arg1, String arg2, boolean arg3) {
        System.out.println("------------------onClose-------------------");
    }

    /**
     * 错误发生时调用。
     */
    @Override
    public void onError(WebSocket ws, Exception e) {
        System.out.println("------------------onError-------------------");
        if(ws != null) {
        }
        e.getStackTrace();
    }

    /**
     * 接收到的消息
     */
    @Override
    public void onMessage(WebSocket ws, String msg) {
        System.out.println("收到消息："+msg);
        //收到什么消息，回复什么
        ws.send(msg);
        if(ws.isClosed()) {
        } else if (ws.isClosing()) {
            System.out.println("ws连接正在关闭...");
        } else if (ws.isConnecting()) {
            System.out.println("ws正在连接中...");
        } else if(ws.isOpen()) {
            System.out.println("ws连接已打开...");
            System.out.println(msg);
        }
    }

    /**
     * websocket进行握手之后调用，并且给WebSocket写做准备
     * 通过握手可以获取请求头信息
     */
    @Override
    public void onOpen(WebSocket ws, ClientHandshake shake) {
        System.out.println("-----------------onOpen--------------------"+ws.isOpen()+"--"+ws.getReadyState()+"--"+ws.getAttachment());
        for(Iterator<String> it=shake.iterateHttpFields();it.hasNext();) {
            String key = it.next();
            System.out.println(key+":"+shake.getFieldValue(key));
        }
    }
    /**
     * 当服务器成功启动时调用
     */
    @Override
    public void onStart() {
        System.out.println("------------------onStart-------------------");
    }
}

