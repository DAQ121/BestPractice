package websocket;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Optional;

@Slf4j
public class WSServer extends WebSocketServer {

    private MessageHandler messageHandler;

    public static WSServer createWSServer(WebSocketSetting setting) {
        String host = Optional.ofNullable(setting.getHost())
                .orElse("127.0.0.1");

        InetSocketAddress socketAddress = new InetSocketAddress(host, setting.getPort());
        return new WSServer(socketAddress);
    }

    public WSServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        log.info("new connection to " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        log.info("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        if (this.messageHandler != null) {
            this.messageHandler.handleMessage(message);
        }
    }

    @Override
    public void onMessage( WebSocket conn, ByteBuffer message ) {
        log.info("received ByteBuffer from "	+ conn.getRemoteSocketAddress());
        if (this.messageHandler != null) {
            this.messageHandler.handleMessage(message);
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        log.info("an error occurred on connection " + conn.getRemoteSocketAddress()  + ":" + ex.getMessage());
    }

    @Override
    public void onStart() {
        log.info("websocket server started at {} port successfully", super.getPort());
    }

    /**
     * 添加对消息的业务处理
     */
    public WSServer addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
        return this;
    }

}