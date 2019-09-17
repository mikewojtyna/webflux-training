package pro.buildmysoftware.webflux.operators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Random;

public class ShareFluxExamplesTest {
	@DisplayName("share flux example")
	@Test
	void test() throws Exception {
		Flux<Integer> integerFlux = Flux.<Integer>create(sink -> {
			blockProduce(sink);
		}).share();

		integerFlux.subscribe(i -> System.out
			.println("First " + "subscriber: " + i));
		Thread.sleep(1000);
		integerFlux.subscribe(i -> System.out
			.println("Second " + "subscriber: " + i));

		Thread.sleep(5000);
	}

	private void blockProduce(FluxSink<Integer> sink) {
		new Thread(() -> {
			while (true) {
				sink.next(new Random().nextInt());
				try {
					Thread.sleep(1000);
				}
				catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}).start();
	}
}
