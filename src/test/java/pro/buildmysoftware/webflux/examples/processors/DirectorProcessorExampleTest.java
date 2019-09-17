package pro.buildmysoftware.webflux.examples.processors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public class DirectorProcessorExampleTest {
	@DisplayName("direct processor example (documentation example)")
	@Test
	void test() throws Exception {
		DirectProcessor<String> hotSource = DirectProcessor.create();
		Flux<String> hotFlux = hotSource.map(String::toUpperCase);
		FluxSink<String> sink = hotSource.sink();

		hotFlux.subscribe(d -> System.out
			.println("Subscriber 1 to Hot Source: " + d));

		sink.next("blue");
		sink.next("green");

		hotFlux.subscribe(d -> System.out
			.println("Subscriber 2 to Hot Source: " + d));

		sink.next("orange");
		sink.next("purple");
		sink.complete();
	}
}
