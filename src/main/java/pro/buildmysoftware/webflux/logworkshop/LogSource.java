package pro.buildmysoftware.webflux.logworkshop;

import org.reactivestreams.Publisher;
import pro.buildmysoftware.webflux.log.LogEntry;

public interface LogSource {
	Publisher<LogEntry> source();
}
