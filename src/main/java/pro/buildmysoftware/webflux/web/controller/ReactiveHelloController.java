package pro.buildmysoftware.webflux.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.buildmysoftware.webflux.web.Hello;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/controller/hello")
public class ReactiveHelloController {
	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Hello> allMsgs() {
		return Flux.interval(Duration.ofSeconds(1))
			.map(i -> new Hello("Hello " + i));
	}
}
