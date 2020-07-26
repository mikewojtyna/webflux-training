package pro.buildmysoftware.webflux.stacjait.conference;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pro.buildmysoftware.webflux.stacjait.conference.domainmodel.Conference;
import pro.buildmysoftware.webflux.stacjait.conference.domainmodel.ConferenceId;
import pro.buildmysoftware.webflux.stacjait.conference.domainmodel.ConferenceRepository;

@Component
public class DefaultConferenceInitializer implements ApplicationRunner {

	private ConferenceRepository repository;

	public DefaultConferenceInitializer(ConferenceRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		repository.save(new Conference(new ConferenceId("conf0")));
	}
}
