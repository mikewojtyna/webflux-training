package pro.buildmysoftware.webflux.stacjait.log;

import org.reactivestreams.Publisher;

import java.util.function.Predicate;

public interface LogProcessor {

	Publisher<LogEntry> filter(LogSource logSource,
				   Predicate<LogEntry> predicate);
}
