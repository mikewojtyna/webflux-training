package pro.buildmysoftware.webflux.examples.operators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class FiniteAndInfiniteFluxExamplesTest {

	@DisplayName("show map operator")
	@Test
	void mapFinite() throws Exception {
		Flux<String> flux = Flux.just("hi", "hello");

		flux.log().map(String::length).filter(l -> l > 2)
			.subscribe(System.out::println);
	}

	@DisplayName("show working on infinite stream")
	@Test
	void infinite() throws Exception {
		Flux<Long> flux = Flux.interval(Duration.ofSeconds(1));

		flux.log().subscribe(l -> {
			try {
				System.out.println("Subscriber 0: " + l);
				Thread.sleep(5000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		flux.log().subscribe(l -> System.out
			.println("Subscriber 1: " + l));

		Thread.sleep(2000);
	}
}
