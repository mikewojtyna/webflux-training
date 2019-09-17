package pro.buildmysoftware.webflux.operators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ParallelExampleTest {
	// @formatter:off
	@DisplayName(
		"show how to use parallel mapping"
	)
	// @formatter:on
	@Test
	void parallel() throws Exception {
		Flux.range(1, 10).parallel().runOn(Schedulers.elastic())
			.map(i -> {
				try {
					Thread.sleep(1000);
					return i;
				}
				catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}).log().subscribe(System.out::println);

		Thread.sleep(2000);
	}

	// @formatter:off
	@DisplayName(
		"show how NOT to use parallel mapping"
	)
	// @formatter:on
	@Test
	void parallelBad() throws Exception {
		Flux.range(1, 10).parallel()
			// Only this line changed - map is invoked before
			// runOn! Therefore, it doesn't run on elastic
			// scheduler.
			.map(i -> {
				try {
					Thread.sleep(1000);
					return i;
				}
				catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}).runOn(Schedulers.elastic()).log()
			.subscribe(System.out::println);

		// we don't even need Thread.sleep here to see the problem
		// (mapping happens on a single thread)
	}
}
