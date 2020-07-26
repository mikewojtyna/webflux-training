package pro.buildmysoftware.webflux.stacjait.conference.readmodel;

import org.reactivestreams.Publisher;
import org.springframework.context.event.EventListener;
import pro.buildmysoftware.webflux.stacjait.conference.domainmodel.UserId;
import pro.buildmysoftware.webflux.stacjait.conference.domainmodel.UserJoinedConference;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SpringEventsConferenceStateMonitor implements ConferenceStateMonitor {

	private Collection<Consumer<UserEvent>> eventConsumers;
	private UserEvent userJoinedRoom;

	public SpringEventsConferenceStateMonitor() {
		eventConsumers = new ArrayList<>();
	}

	@Override
	public Publisher<UserEvent> userEvents() {
		return Mono.justOrEmpty(userJoinedRoom).concatWith(Flux
			.create(fluxSink -> eventConsumers
				.add(fluxSink::next)));
	}

	@EventListener
	public void handle(UserJoinedConference userJoinedConference) {
		userJoinedRoom = translateToUserEvent(userJoinedConference);
		eventConsumers
			.forEach(consumer -> consumer.accept(userJoinedRoom));
	}

	private UserEvent translateToUserEvent(UserJoinedConference userJoinedConference) {
		return new UserJoinedRoom(userJoinedConference.getRoom()
			.getValue(), userJoinedConference.getUser()
			.getValue(), new State(userJoinedConference.getRoom()
			.getValue(),
			userJoinedConference.getAllUsers().stream()
			.map(UserId::getValue)
			.collect(Collectors.toUnmodifiableList())));
	}
}
