package pro.buildmysoftware.webflux.examples.stacjait;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

class MonoExamplesTest {

	// @formatter:off
	@DisplayName(
		"show simple mono map example"
	)
	//@formatter:on
	@Test
	void monoSimple() throws Exception {
		Mono.just("hello").map(String::length)
			.subscribe(System.out::println);
	}

	// @formatter:off
	@DisplayName(
		"given publisher interface (from any implementation), " +
		"then we can create specific project reactor implementation"
	)
	//@formatter:on
	@Test
	void convertToMono() throws Exception {
		Publisher<String> publisher =
			getPublisherFromAnyImplementation();
		Mono<String> mono = Mono.from(publisher);
		mono.map(String::length).subscribe(System.out::println);
	}

	private Publisher<String> getPublisherFromAnyImplementation() {
		// here you can have any implementation of publisher
		return Mono.just("hello");
	}
}
