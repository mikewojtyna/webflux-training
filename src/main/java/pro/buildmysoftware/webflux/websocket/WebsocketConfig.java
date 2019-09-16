package pro.buildmysoftware.webflux.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.Map;

@Configuration
public class WebsocketConfig {
	@Bean
	public HandlerMapping handlerMapping(WebsocketNumbersHandler numbersHandler, WebsocketEchoHandler echoHandler) {
		SimpleUrlHandlerMapping simpleUrlHandlerMapping =
			new SimpleUrlHandlerMapping();
		simpleUrlHandlerMapping.setUrlMap(Map
			.of("/websocket/hello", numbersHandler,
				"/websocket" + "/echo", echoHandler));
		simpleUrlHandlerMapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return simpleUrlHandlerMapping;
	}

	@Bean
	public WebsocketNumbersHandler numbersHandler() {
		return new WebsocketNumbersHandler();
	}

	@Bean
	public WebsocketEchoHandler echoHandler() {
		return new WebsocketEchoHandler();
	}

	@Bean
	public WebSocketHandlerAdapter adapter() {
		return new WebSocketHandlerAdapter();
	}
}
