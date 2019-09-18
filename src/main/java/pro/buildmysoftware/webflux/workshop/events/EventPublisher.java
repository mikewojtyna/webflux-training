package pro.buildmysoftware.webflux.workshop.events;

import org.springframework.context.ApplicationEventPublisher;

public class EventPublisher {
	private ApplicationEventPublisher eventPublisher;

	public EventPublisher(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	public void publishSomeMessages(String... messages) {
		for (String msg : messages) {
			eventPublisher.publishEvent(new Event(msg));
		}
	}
}
