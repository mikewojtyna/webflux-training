package pro.buildmysoftware.webflux.stacjait.log;

import org.reactivestreams.Publisher;

public interface LogSource {

	Publisher<LogEntry> logs();
}
