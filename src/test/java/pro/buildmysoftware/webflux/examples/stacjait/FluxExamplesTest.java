package pro.buildmysoftware.webflux.examples.stacjait;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

class FluxExamplesTest {

	// @formatter:off
	@DisplayName(
		"show basic flux example"
	)
	//@formatter:on
	@Test
	void fluxExampleBasic() throws Exception {
		Flux<String> flux = Flux.just("hi", "hello", "welcome");
		flux.subscribe(System.out::println);
	}
}
