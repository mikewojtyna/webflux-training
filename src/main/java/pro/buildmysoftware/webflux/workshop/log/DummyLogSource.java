package pro.buildmysoftware.webflux.workshop.log;

import org.reactivestreams.Publisher;
import pro.buildmysoftware.webflux.log.LogEntry;
import reactor.core.publisher.Flux;

import java.util.Collection;

public class DummyLogSource implements LogSource {
	private Collection<LogEntry> dummyEntries;

	public DummyLogSource(Collection<LogEntry> dummyEntries) {
		this.dummyEntries = dummyEntries;
	}

	@Override
	public Publisher<LogEntry> source() {
		return Flux.fromIterable(dummyEntries);
	}
}
