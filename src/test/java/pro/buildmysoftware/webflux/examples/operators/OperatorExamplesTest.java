package pro.buildmysoftware.webflux.examples.operators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pro.buildmysoftware.webflux.web.Hello;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class OperatorExamplesTest {
	@DisplayName("map example")
	@Test
	void mapExample() throws Exception {
		StepVerifier.create(Mono.just("hello").log().map(Hello::new)
			.map(m -> m)).expectNext(new Hello("hello"))
			.expectComplete().verify();
	}

	@DisplayName("flat map example")
	@Test
	void flatMapExample() throws Exception {
		StepVerifier.create(Mono.just("hello").log().map(Hello::new)
			.flatMap(m -> Mono.fromCallable(() -> m)))
			.expectNext(new Hello("hello")).verifyComplete();
	}
}
