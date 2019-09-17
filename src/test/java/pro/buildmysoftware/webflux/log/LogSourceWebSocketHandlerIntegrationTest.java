package pro.buildmysoftware.webflux.log;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LogSourceWebSocketHandlerIntegrationTest {
	private static final LogEntry FAKE_LOG_ENTRY = new LogEntry("INFO",
		"Fake msg");
	@LocalServerPort
	private String port;
	@MockBean
	private LogSource logSource;

	@BeforeEach
	void beforeEach() {
		Mockito.when(logSource.logEntries())
			.thenReturn(Flux.just(FAKE_LOG_ENTRY));
	}

	// @formatter:off
	@DisplayName(
		"when request log source web socket endpoint, " + "then " +
		"log entries are returned according to configured mock " +
		"log source"
	)
	// @formatter:on
	@Test
	void test() throws Exception {
		// given
		Collection<String> collectedLogEntries = new ArrayList<>();
		WebSocketClient client = new ReactorNettyWebSocketClient();
		WebSocketHandler handler = session -> session.receive().take(3)
			.map(WebSocketMessage::getPayloadAsText).log()
			.doOnNext(logEntry -> {
				collectedLogEntries.add(logEntry);
			}).then();

		// when
		client.execute(uri("/websocket/log"), handler)
			.block(Duration.ofSeconds(5));

		// then
		assertThat(collectedLogEntries)
			// @formatter:off
			.containsExactlyInAnyOrder(
				"{\"level\":\"INFO\",\"msg\":\"Fake msg\"}"
			// @formatter:on
			);
	}

	private URI uri(String endpoint) throws URISyntaxException {
		return new URI(String
			.format("ws://localhost:%s%s", port, endpoint));
	}
}
