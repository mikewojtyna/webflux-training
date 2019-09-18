package pro.buildmysoftware.webflux.workshop.events;

import reactor.core.publisher.Flux;

public class EventLogicHandler {
	public Flux<Event> handle(Flux<Event> eventSource) {
		// place any logic inside
		return eventSource.map(event -> event);
	}
}
