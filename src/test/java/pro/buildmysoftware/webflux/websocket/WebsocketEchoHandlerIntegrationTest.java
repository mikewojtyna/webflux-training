package pro.buildmysoftware.webflux.websocket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebsocketEchoHandlerIntegrationTest {
	private static final long TIMEOUT_SECS = 5;
	@LocalServerPort
	private String port;

	// @formatter:off
	@DisplayName(
		"given 3 messages, " +
		"when send to echo websocket, " +
		"then the same 3 messages are returned"
	)
	// @formatter:on
	@Test
	void test() throws Exception {
		// given
		WebSocketClient client = new ReactorNettyWebSocketClient();
		List<String> messages = List.of("hi", "hello", "greetings");
		List<String> receivedMessages = new ArrayList<>();

		// when
		sendAllMsgs(client, messages, receivedMessages);

		// then
		assertThat(receivedMessages)
			.containsExactly("hi", "hello", "greetings");
	}

	private void sendAllMsgs(WebSocketClient client, List<String> allMsgs,
				 List<String> target) throws URISyntaxException, InterruptedException {
		client.execute(uri("/websocket/echo"), session -> session
			.send(Flux.fromIterable(allMsgs)
				.map(session::textMessage))
			.thenMany(session.receive().take(allMsgs.size())
				.map(WebSocketMessage::getPayloadAsText)
				.log("echo client receive"))
			.doOnNext(target::add).then())
			.block(Duration.ofSeconds(TIMEOUT_SECS));
	}

	private URI uri(String endpoint) throws URISyntaxException {
		return new URI(String
			.format("ws://localhost:%s%s", port, endpoint));
	}
}
