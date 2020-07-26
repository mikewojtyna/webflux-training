package pro.buildmysoftware.webflux.stacjait.conference.domainmodel;

public interface DomainEventPublisher {

	void publish(UserJoinedConference userJoinedConference);
}
