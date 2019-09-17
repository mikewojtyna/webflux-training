package pro.buildmysoftware.webflux.workshop.log;

import org.reactivestreams.Publisher;
import pro.buildmysoftware.webflux.log.LogEntry;

public interface LogSource {
	Publisher<LogEntry> source();
}
