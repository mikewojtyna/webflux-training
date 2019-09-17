package pro.buildmysoftware.webflux.operators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class ColdAndHotStreamsExampleTest {
	// @formatter:off
	@DisplayName(
		"cold stream example"
	)
	// @formatter:on
	@Test
	void cold() throws Exception {
		Flux<Long> ticks = Flux.interval(Duration.ofSeconds(1));

		ticks.subscribe(System.out::println);
		Thread.sleep(2000);
		ticks.subscribe(System.out::println);

		Thread.sleep(5000);
	}

	// @formatter:off
	@DisplayName(
		"hot stream example"
	)
	// @formatter:on
	@Test
	void hot() throws Exception {
		Flux<Long> ticks =
			Flux.interval(Duration.ofSeconds(1)).share();

		ticks.subscribe(System.out::println);
		Thread.sleep(2000);
		ticks.subscribe(System.out::println);

		Thread.sleep(5000);
	}

	// @formatter:off
	@DisplayName(
		"hot stream still requires at least subscription example"
	)
	// @formatter:on
	@Test
	void hotSubscribe() throws Exception {
		Flux<Long> ticks = Flux.interval(Duration.ofSeconds(1))
			.doOnNext(System.out::println).share();

		Thread.sleep(2000);
	}
}
