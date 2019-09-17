package pro.buildmysoftware.webflux.examples.processors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;

public class DirectorProcessorExampleTest {
	@DisplayName("direct processor example (documentation example)")
	@Test
	void test() throws Exception {
		DirectProcessor<String> hotSource = DirectProcessor.create();

		Flux<String> hotFlux = hotSource.map(String::toUpperCase);


		hotFlux.subscribe(d -> System.out
			.println("Subscriber 1 to Hot Source: " + d));

		hotSource.onNext("blue");
		hotSource.onNext("green");

		hotFlux.subscribe(d -> System.out
			.println("Subscriber 2 to Hot Source: " + d));

		hotSource.onNext("orange");
		hotSource.onNext("purple");
		hotSource.onComplete();
	}
}
