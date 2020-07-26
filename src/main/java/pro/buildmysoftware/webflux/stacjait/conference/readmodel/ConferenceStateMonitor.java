package pro.buildmysoftware.webflux.stacjait.conference.readmodel;

import org.reactivestreams.Publisher;

public interface ConferenceStateMonitor {

	Publisher<UserEvent> userEvents();
}
