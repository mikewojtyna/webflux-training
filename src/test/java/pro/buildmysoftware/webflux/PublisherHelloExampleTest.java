package pro.buildmysoftware.webflux;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PublisherHelloExampleTest {
	@DisplayName("show mono example")
	@Test
	void mono() throws Exception {
		Publisher<String> publisher = justHello();

		Mono<String> mono = Mono.from(publisher);

		mono.subscribe(System.out::println);
		mono.subscribe(System.out::println);
	}

	@DisplayName("show flux example")
	@Test
	void flux() throws Exception {
		Publisher<String> publisher = fluxWithTwoMessages();

		Flux.from(publisher).subscribe(System.out::println);
		Flux.from(publisher).subscribe(System.out::println);
	}

	private Publisher<String> fluxWithTwoMessages() {
		return Flux.just("hi", "hello");
	}

	private Publisher<String> justHello() {
		return Mono.just("hello");
	}
}
