package websocket;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.Map;

@Slf4j
public class WSClient extends WebSocketClient {

	private MessageHandler messageHandler;

	public static WSClient createWSClient(WebSocketSetting setting) {
		WSClient wsClient = null;

		try {
			URI serverUri = new URI(setting.getUrl());
			Map<String, String> httpHeaders = setting.getHttpHeaders();
			int connectTimeout = setting.getConnectTimeout();

			wsClient = new WSClient(serverUri, httpHeaders, connectTimeout);
		} catch (URISyntaxException e) {
			log.error("your websocket url {} is not availed", setting.getUrl());
		}
		return wsClient;
	}

	public WSClient(URI serverURI, Map<String, String> httpHeaders, int connectTimeout) {
		super(serverURI, new Draft_6455(), httpHeaders, connectTimeout);
	}

	@Override
	public void onOpen(ServerHandshake handshake) {
		log.info("open this websocket {} ", this.getLocalSocketAddress());
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		log.info("closed with exit code {} additional info: {}", code, reason);
	}

	@Override
	public void onMessage(String message) {
		if (this.messageHandler != null) {
			this.messageHandler.handleMessage(message);
		}
	}

	@Override
	public void onMessage(ByteBuffer message) {
		// 对消息的业务处理
		if (this.messageHandler != null) {
			this.messageHandler.handleMessage(message);
		}
	}

	@Override
	public void onError(Exception ex) {
		log.error("an error occurred: {}", ex.getMessage());
	}

	/**
	 * 添加对消息的业务处理
	 */
	public WSClient addMessageHandler(MessageHandler msgHandler) {
		this.messageHandler = msgHandler;
		return this;
	}
}