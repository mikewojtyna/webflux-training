package pro.buildmysoftware.webflux.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

public class StepVerifierExampleTest {
	// @formatter:off
	@DisplayName(
		"show how to use step verifier"
	)
	// @formatter:on
	@Test
	void test() throws Exception {
		Publisher<String> flux = Flux.just(1, 2, 3)
			.map(String::valueOf);

		StepVerifier.create(flux)
			// @formatter:off

			.expectSubscription()
			.expectNext("1")
			.assertNext(s -> assertThat(s).isEqualTo(
				"2"))
			.expectNextMatches(s -> "3".equals(s))
// @formatter:on
			.verifyComplete();
	}
}
