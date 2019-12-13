package pro.buildmysoftware.webflux.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;

@RestController
@RequestMapping("/controller/data")
public class FluxConsumerProducerController {

	private WebClient webClient;

	public FluxConsumerProducerController() {
		webClient = WebClient.create();
	}

	@PostMapping
	public void consume(@RequestBody Flux<Data> flux) {
		flux.log("Consumer").subscribe(d -> System.out
			.println("Received data: " + d));
	}

	@GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Data> produce() {
		return Flux
			.just(new Data("data 0"), new Data("data 1"),
				new Data("data 2"))
			.zipWith(Flux.interval(Duration.ofSeconds(1)))
			.map(Tuple2::getT1).log("Producer");
	}

	@GetMapping(params = "callApi", produces =
		MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Data> produceUsingApi() {
		return webClient.get()
			.uri("http://localhost:8080/controller/data").retrieve()
			.bodyToFlux(Data.class);
	}
}
