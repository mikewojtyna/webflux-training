package pro.buildmysoftware.webflux.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class LogSourceWebSocketHandler implements WebSocketHandler {
	private LogSource logSource;
	private ObjectMapper objectMapper;

	public LogSourceWebSocketHandler(LogSource logSource,
					 ObjectMapper objectMapper) {
		this.logSource = logSource;
		this.objectMapper = objectMapper;
	}

	@Override
	public Mono<Void> handle(WebSocketSession session) {
		return session.send(Flux.from(logSource.logEntries())
			.map(this::writeAsJson).map(session::textMessage));
	}

	private String writeAsJson(LogEntry logEntry) {
		try {
			return objectMapper.writeValueAsString(logEntry);
		}
		catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
