package pro.buildmysoftware.webflux.stacjait.conference.domainmodel;

public class ConferenceUseCase {

	private ConferenceRepository conferenceRepository;
	private UserRepository userRepository;
	private DomainEventPublisher domainEventPublisher;

	public ConferenceUseCase(ConferenceRepository conferenceRepository,
				 UserRepository userRepository,
				 DomainEventPublisher domainEventPublisher) {
		this.conferenceRepository = conferenceRepository;
		this.userRepository = userRepository;
		this.domainEventPublisher = domainEventPublisher;
	}

	public void join(UserId user, ConferenceId conferenceId) {
		conferenceRepository.byId(conferenceId)
			.ifPresent(conference -> {
				UserJoinedConference userJoinedConference =
					conference
					.join(userRepository.byId(user)
						.orElseThrow());
				conferenceRepository.save(conference);
				domainEventPublisher
					.publish(userJoinedConference);
			});
	}
}
