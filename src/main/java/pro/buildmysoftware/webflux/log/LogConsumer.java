package pro.buildmysoftware.webflux.log;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

public class LogConsumer {
	private LogSource logSource;

	public LogConsumer(LogSource logSource) {
		this.logSource = logSource;
	}

	public Publisher<String> extractCategories() {
		return Flux.from(logSource.logEntries())
			.map(LogEntry::getCategory);
	}
}
