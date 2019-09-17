package pro.buildmysoftware.webflux.log;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

public class LogLevelExtractor {
	private LogSource logSource;

	public LogLevelExtractor(LogSource logSource) {
		this.logSource = logSource;
	}

	public Publisher<String> extractLogLevels() {
		return Flux.from(logSource.logEntries())
			.map(LogEntry::getLevel);
	}
}
