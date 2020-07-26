package pro.buildmysoftware.webflux.stacjait.log;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.util.function.Predicate;

public class ProjectReactorLogProcessor implements LogProcessor {

	@Override
	public Publisher<LogEntry> filter(LogSource logSource,
					  Predicate<LogEntry> predicate) {
		return Flux.from(logSource.logs()).filter(predicate);
	}
}
