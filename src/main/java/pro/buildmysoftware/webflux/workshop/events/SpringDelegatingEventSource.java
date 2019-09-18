package pro.buildmysoftware.webflux.workshop.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SpringDelegatingEventSource {
	private ReactiveEventSource eventSource;

	public SpringDelegatingEventSource(ReactiveEventSource eventSource) {
		this.eventSource = eventSource;
	}

	@EventListener
	public void handle(Event event) {
		eventSource.handle(event);
	}
}
