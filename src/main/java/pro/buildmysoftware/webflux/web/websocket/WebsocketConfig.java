package pro.buildmysoftware.webflux.web.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import pro.buildmysoftware.webflux.log.LogSource;
import pro.buildmysoftware.webflux.log.LogSourceWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebsocketConfig {

	@Bean
	public HandlerMapping handlerMapping(WebsocketNumbersHandler numbersHandler, WebsocketEchoHandler echoHandler, LogSourceWebSocketHandler logSourceWebSocketHandler) {
		SimpleUrlHandlerMapping simpleUrlHandlerMapping =
			new SimpleUrlHandlerMapping();
		simpleUrlHandlerMapping
			.setUrlMap(mapOf("/websocket/hello", numbersHandler,
				"/websocket" + "/echo", echoHandler,
				"/websocket/log", logSourceWebSocketHandler));
		simpleUrlHandlerMapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return simpleUrlHandlerMapping;
	}

	@Bean
	public LogSource logSource() {
		return new LogSource();
	}

	@Bean
	public LogSourceWebSocketHandler logSourceWebSockerHandler(ObjectMapper objectMapper, LogSource logSource) {
		return new LogSourceWebSocketHandler(logSource, objectMapper);
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

	private Map<String, ?> mapOf(String s,
				     WebsocketNumbersHandler numbersHandler,
				     String s1,
				     WebsocketEchoHandler echoHandler,
				     String s2,
				     LogSourceWebSocketHandler logSourceWebSocketHandler) {
		HashMap<String, Object> map = new HashMap<>();
		map.put(s, numbersHandler);
		map.put(s1, logSourceWebSocketHandler);
		map.put(s2, logSourceWebSocketHandler);
		return map;
	}
}
