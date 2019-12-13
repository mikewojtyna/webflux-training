package pro.buildmysoftware.webflux.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.buildmysoftware.webflux.web.Hello;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

@RestController
@RequestMapping("/controller/greetings")
public class GreetingsController {

	@GetMapping("/hello")
	public Mono<Hello> sayHello() {
		return Mono.just(new Hello("hello"));
	}

	@GetMapping
	public Flux<Hello> sayAllGreetings() {
		return Flux
			.just(new Hello("hi"), new Hello("hello"), new Hello(
				"welcome"));
	}

	@GetMapping("/long")
	public Flux<Hello> longGreeting() {
		return Flux.just(new Hello("hi"), new Hello("hello"))
			.zipWith(Flux.interval(Duration.ofSeconds(2)))
			.map(Tuple2::getT1);
	}
}
