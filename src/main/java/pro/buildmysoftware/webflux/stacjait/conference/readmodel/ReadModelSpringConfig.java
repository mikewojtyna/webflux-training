package pro.buildmysoftware.webflux.stacjait.conference.readmodel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReadModelSpringConfig {

	@Bean
	public ConferenceStateMonitor conferenceStateMonitor() {
		return new SpringEventsConferenceStateMonitor();
	}
}
