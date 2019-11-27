package pro.buildmysoftware.webflux.examples.operators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class FlatMapExamplesTest {

	@DisplayName("flat map blocking example")
	@Test
	void test() throws Exception {
		Flux.range(1, 10).log()
			.flatMap(i -> Mono.fromRunnable(() -> blockingTask())
				.subscribeOn(Schedulers.elastic()), 3)
			.blockLast();
	}

	private void blockingTask() {
		try {
			Thread.sleep(2000);
			System.out.println("hello");
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
