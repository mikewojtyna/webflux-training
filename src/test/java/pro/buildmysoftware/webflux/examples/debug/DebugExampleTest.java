package pro.buildmysoftware.webflux.examples.debug;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

public class DebugExampleTest {
	// @formatter:off
	@DisplayName(
		"show debugging example using global hooks (costly)"
	)
	// @formatter:on
	@Test
	void test0() throws Exception {
		Hooks.onOperatorDebug();
		Flux<String> flux =
			Flux.just(1, 2).log().flatMap(this::handle);
		flux.subscribe();
	}

	// @formatter:off
	@DisplayName(
		"show debugging example using checkpoint"
	)
	// @formatter:on
	@Test
	void test1() throws Exception {
		Flux<String> flux = Flux.just(1, 2).log().flatMap(this::handle)
			// checkpoint should be constructed near the end of
			// the stream, to be able to instrument all operations
			.checkpoint();
		flux.subscribe();
	}

	private Publisher<String> handle(int i) {
		throw new RuntimeException("exception when processing " + i);
	}
}
