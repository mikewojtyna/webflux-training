package pro.buildmysoftware.webflux.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SubscribeAndPublishOnTest {
	// @formatter:off
	@DisplayName("subscribeOn example")
	// @formatter:on
	@Test
	void subscribeOn() throws Exception {
		Flux<Integer> flux = Flux.range(1, 10);
		System.out.println("Without subscribeOn (default thread)");
		flux.log().blockLast();
		System.out.println("With subscribeOn using single scheduler");
		flux.subscribeOn(Schedulers.single()).log().blockLast();
	}

	// @formatter:off
	@DisplayName("publishOn example")
	// @formatter:on
	@Test
	void publishOn() throws Exception {
		Flux<Integer> flux = Flux.range(1, 10);
		System.out.println("Publish on before log");
		flux.publishOn(Schedulers.single()).log().blockLast();
		System.out.println("Publish on after log");
		flux.log().publishOn(Schedulers.single()).blockLast();
	}
}
