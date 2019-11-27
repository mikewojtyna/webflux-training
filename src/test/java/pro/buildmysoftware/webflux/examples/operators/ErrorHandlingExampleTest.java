package pro.buildmysoftware.webflux.examples.operators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ErrorHandlingExampleTest {

	// @formatter:off
	@DisplayName(
		"return default error value on exception"
	)
	// @formatter:on
	@Test
	void test0() throws Exception {
		Integer defaultValue = 10;
		Flux.generate(sink -> {
			sink.next(1);
			sink.error(new RuntimeException("exception"));
		}).onErrorReturn(defaultValue).subscribe(System.out::println);
	}

	// @formatter:off
	@DisplayName(
		"use resume on exception"
	)
	// @formatter:on
	@Test
	void test1() throws Exception {
		Publisher<Integer> fallbackPublisher = Flux.just(10, 11, 12);

		Flux.generate(sink -> {
			sink.next(1);
			sink.error(new RuntimeException("exception"));
		}).onErrorResume(throwable -> {
			if (throwable instanceof RuntimeException) {
				return fallbackPublisher;
			}
			return Mono.error(throwable);
		}).subscribe(System.out::println);
	}
}
