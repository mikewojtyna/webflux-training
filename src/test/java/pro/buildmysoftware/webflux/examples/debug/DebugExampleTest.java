package pro.buildmysoftware.webflux.examples.debug;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

public class DebugExampleTest {
	// @formatter:off
	@DisplayName(
		"show debugging example"
	)
	// @formatter:on
	@Test
	void test() throws Exception {
		Hooks.onOperatorDebug();
		Flux<String> flux = Flux.just(1, 2).log().flatMap(this::handle)
			.log();
		flux.subscribe();
	}

	private Publisher<String> handle(int i) {
		throw new RuntimeException();
	}
}
