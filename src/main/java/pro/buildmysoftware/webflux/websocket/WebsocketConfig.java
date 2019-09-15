package pro.buildmysoftware.webflux.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.Map;

@Configuration
public class WebsocketConfig {
	@Bean
	public HandlerMapping handlerMapping(WebSocketHandler handler) {
		SimpleUrlHandlerMapping simpleUrlHandlerMapping =
			new SimpleUrlHandlerMapping();
		simpleUrlHandlerMapping
			.setUrlMap(Map.of("/websocket/hello", handler));
		simpleUrlHandlerMapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return simpleUrlHandlerMapping;
	}

	@Bean
	public WebSocketHandler handler() {
		return new WebsocketNumbersHandler();
	}

	@Bean
	public WebSocketHandlerAdapter adapter() {
		return new WebSocketHandlerAdapter();
	}
}
