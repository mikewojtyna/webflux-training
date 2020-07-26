package pro.buildmysoftware.webflux.stacjait.log;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Configuration
public class StacjaItLogSourceConfig {

	@Bean
	public LogSource stacjaItLogSource() {
		return () -> Flux.interval(Duration.ofSeconds(1)).map(tick -> {
			int modulo = (int) (tick % 3);
			switch (modulo) {
				case 0:
					return new LogEntry(Level.INFO);
				case 1:
					return new LogEntry(Level.WARN);
				default:
					return new LogEntry(Level.ERROR);
			}
		});
		/*return () -> Flux
			.just(new LogEntry(Level.INFO),
				new LogEntry(Level.INFO),
				new LogEntry(Level.INFO),
				new LogEntry(Level.INFO),
				new LogEntry(Level.WARN),
				new LogEntry(Level.WARN),
				new LogEntry(Level.ERROR),
				new LogEntry(Level.ERROR),
				new LogEntry(Level.ERROR),
				new LogEntry(Level.WARN));*/
	}

	@Bean
	public LogProcessor stacjaItLogProcessor() {
		return new ProjectReactorLogProcessor();
	}
}
