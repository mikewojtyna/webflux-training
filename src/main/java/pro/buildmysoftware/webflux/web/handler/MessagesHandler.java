package pro.buildmysoftware.webflux.web.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class MessagesHandler {

	private Flux<String> sharedFlux;

	public MessagesHandler() {
		sharedFlux = Flux.interval(Duration.ofSeconds(5)).log()
			.map(n -> "Message: " + n).share();
	}

	public Mono<ServerResponse> handle(ServerRequest request) {
		return ServerResponse.ok()
			.contentType(MediaType.TEXT_EVENT_STREAM)
			.body(produceMsgs(), String.class);
	}

	private Flux<String> produceMsgs() {
		return sharedFlux;
	}
}
