package pro.buildmysoftware.webflux.stacjait.conference.domainmodel;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Configuration
public class ConferenceDomainSpringConfig {

	@Bean
	public ConferenceUseCase conferenceUseCase(ConferenceRepository conferenceRepo, UserRepository userRepository, ApplicationEventPublisher applicationEventPublisher) {
		return new ConferenceUseCase(conferenceRepo, userRepository,
			applicationEventPublisher::publishEvent);
	}

	@Bean
	public ConferenceRepository conferenceRepo() {
		return new ConferenceRepository() {

			private ConcurrentMap<ConferenceId, Conference> map =
				new ConcurrentHashMap<>();

			@Override
			public Optional<Conference> byId(ConferenceId id) {
				return Optional.ofNullable(map.get(id));
			}

			@Override
			public void save(Conference conference) {
				map.put(conference.id(), conference);
			}
		};
	}

	@Bean
	public UserRepository userRepository() {
		return new UserRepository() {
			@Override
			public Optional<User> byId(UserId id) {
				return Optional.of(new User(id));
			}
		};
	}
}
