package websocket;

@FunctionalInterface
public interface MessageHandler<T> {

	/**
	 * 处理消息
	 * @param message
	 */
	void handleMessage(T message);

}