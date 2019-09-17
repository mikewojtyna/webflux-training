package pro.buildmysoftware.webflux.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class AutoConnectExampleTest {
	@DisplayName("auto connect example (from documentation)")
	@Test
	void test() throws Exception {
		Flux<Integer> source = Flux.range(1, 3)
			.doOnSubscribe(s -> System.out
				.println("subscribed to source"));

		Flux<Integer> autoCo = source.publish().autoConnect(2);

		autoCo.subscribe(System.out::println, e -> {
		}, () -> {
		});
		System.out.println("subscribed first");
		System.out.println("subscribing second");
		autoCo.subscribe(System.out::println, e -> {
		}, () -> {
		});
	}
}
