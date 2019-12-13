package pro.buildmysoftware.webflux.web.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import pro.buildmysoftware.webflux.web.Hello;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class HandlerConfig {

	@Bean
	public RouterFunction<ServerResponse> route(HelloHandler helloHandler,
						    MessagesHandler messagesHandler) {
		// @formatter:off
		return RouterFunctions.route(GET("/hello"),
			helloHandler::hello)
			.and(
				RouterFunctions.route(GET("/messages"),
					messagesHandler::handle))
			.and(RouterFunctions.route(
				GET("/messages2"),
				serverRequest -> ServerResponse.ok().body(Mono.just(new Hello("hi")), Hello.class)))
		// @formatter:on
			;
	}
}
