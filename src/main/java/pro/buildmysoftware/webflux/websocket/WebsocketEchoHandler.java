package pro.buildmysoftware.webflux.websocket;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

public class WebsocketEchoHandler implements WebSocketHandler {
	@Override
	public Mono<Void> handle(WebSocketSession session) {
		return session.send(session.receive()
			// retain is required by netty runtime
			.doOnNext(WebSocketMessage::retain)
			.log("echo handler"));
	}
}
