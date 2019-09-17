package pro.buildmysoftware.webflux.web.handler;

import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import pro.buildmysoftware.webflux.web.Hello;
import reactor.core.publisher.Mono;

@Component
public class HelloHandler {
	public Mono<ServerResponse> hello() {
		return ServerResponse.ok()
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.body(bodyInserter());
	}

	private BodyInserter<?, ? super ServerHttpResponse> bodyInserter() {
		return BodyInserters.fromObject(new Hello("hello"));
	}
}
