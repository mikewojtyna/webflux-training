package pro.buildmysoftware.webflux.web.websocket;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class WebsocketNumbersHandler implements WebSocketHandler {
	@Override
	public Mono<Void> handle(WebSocketSession webSocketSession) {
		return webSocketSession
			.send(Flux.range(1, 3).map(String::valueOf)
				.map(webSocketSession::textMessage));
	}
}
