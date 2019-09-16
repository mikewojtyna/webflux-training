package pro.buildmysoftware.webflux.log;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class LogSource {
	Publisher<LogEntry> logEntries() {
		return Flux.interval(Duration.ofSeconds(1))
			.map(l -> new LogEntry("INFO", "Msg " + l));
	}
}
