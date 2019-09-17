package pro.buildmysoftware.webflux.workshop.log;

import org.reactivestreams.Publisher;
import pro.buildmysoftware.webflux.log.LogEntry;
import reactor.core.publisher.Flux;

public class SimpleMessageExtractor implements LogMessageExtractor {
	@Override
	public Publisher<String> extractMsgOfLevel(String level,
						   LogSource logSource) {
		return Flux.from(logSource.source())
			.filter(entry -> level.equals(entry.getLevel()))
			.map(LogEntry::getMsg);
	}
}
