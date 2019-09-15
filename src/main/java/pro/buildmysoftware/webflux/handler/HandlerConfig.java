package pro.buildmysoftware.webflux.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class HandlerConfig {
	@Bean
	public RouterFunction<ServerResponse> route(HelloHandler helloHandler) {
		return RouterFunctions.route(RequestPredicates
			.GET("/hello"), request -> helloHandler.hello());
	}
}
