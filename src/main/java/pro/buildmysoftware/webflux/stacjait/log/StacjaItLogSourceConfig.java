package pro.buildmysoftware.webflux.stacjait.log;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@Configuration
public class StacjaItLogSourceConfig {

	@Bean
	public LogSource stacjaItLogSource() {
		return () -> Flux
			.just(new LogEntry(Level.INFO),
				new LogEntry(Level.INFO),
				new LogEntry(Level.INFO),
				new LogEntry(Level.INFO),
				new LogEntry(Level.WARN),
				new LogEntry(Level.WARN),
				new LogEntry(Level.ERROR),
				new LogEntry(Level.ERROR),
				new LogEntry(Level.ERROR),
				new LogEntry(Level.WARN));
	}

	@Bean
	public LogProcessor stacjaItLogProcessor() {
		return new ProjectReactorLogProcessor();
	}
}
