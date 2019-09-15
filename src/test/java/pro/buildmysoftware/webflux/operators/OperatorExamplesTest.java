package pro.buildmysoftware.webflux.operators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pro.buildmysoftware.webflux.hello.HelloHandler;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class OperatorExamplesTest {
	@DisplayName("map example")
	@Test
	void mapExample() throws Exception {
		StepVerifier.create(Mono.just("hello").log()
			.map(HelloHandler.Hello::new).map(m -> m))
			.expectNext(new HelloHandler.Hello("hello"))
			.expectComplete().verify();
	}

	@DisplayName("flat map example")
	@Test
	void flatMapExample() throws Exception {
		StepVerifier.create(Mono.just("hello").log()
			.map(HelloHandler.Hello::new)
			.flatMap(m -> Mono.fromCallable(() -> m)))
			.expectNext(new HelloHandler.Hello("hello"))
			.verifyComplete();
	}
}
