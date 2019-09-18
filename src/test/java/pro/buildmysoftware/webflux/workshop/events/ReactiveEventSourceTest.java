package pro.buildmysoftware.webflux.workshop.events;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

public class ReactiveEventSourceTest {
	@DisplayName("when add 3 elements, then source emits 3 signals")
	@Test
	void test() throws Exception {
		// given
		Collection<Event> receivedEvents = new ArrayList<>();
		ReactiveEventSource eventSource =
			new ReactiveEventSource(Executors
			.newSingleThreadExecutor());
		Event event0 = new Event("event 0");
		eventSource.handle(event0);
		Event event1 = new Event("event 1");
		eventSource.handle(event1);
		Event event2 = new Event("event 2");
		eventSource.handle(event2);

		// when
		Flux.create(eventSource).take(3).log()
			.doOnNext(receivedEvents::add)
			.blockLast(Duration.ofSeconds(5));

		// then
		assertThat(receivedEvents)
			.containsExactlyInAnyOrder(event0, event1, event2);
	}
}
