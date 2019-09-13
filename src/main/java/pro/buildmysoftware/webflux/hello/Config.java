package pro.buildmysoftware.webflux.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class Config {
	@Bean
	public RouterFunction<ServerResponse> route(HelloHandler helloHandler) {
		return RouterFunctions.route(RequestPredicates
			.GET("/hello"), request -> helloHandler.hello());
	}
}
