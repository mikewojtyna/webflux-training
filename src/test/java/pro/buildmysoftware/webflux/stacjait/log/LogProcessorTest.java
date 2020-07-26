package pro.buildmysoftware.webflux.stacjait.log;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

class LogProcessorTest {

	// @formatter:off
	@DisplayName(
		"given log source producing 3 log entries of level INFO, WARN and ERROR, " +
		"when process log source using predicate filtering WARN entries, " +
		"then only single log entry is finally published"
	)
	//@formatter:on
	@Test
	void chooseWarnEntries() throws Exception {
		// given
		LogProcessor logProcessor = logProcessor();
		LogEntry warnEntry = warnEntry();
		LogSource logSource = sourceProducingEntries(infoEntry(),
			warnEntry, errorEntry());

		// when
		Publisher<LogEntry> publisherOfOnlyWarnEntries = logProcessor
			.filter(logSource, logEntry -> logEntry.getLevel()
				.equals(Level.WARN));

		// then
		StepVerifier.create(publisherOfOnlyWarnEntries)
			.expectNext(warnEntry).thenCancel()
			.verify(Duration.ofSeconds(2));
	}

	private LogEntry errorEntry() {
		return new LogEntry(Level.ERROR);
	}

	private LogEntry warnEntry() {
		return new LogEntry(Level.WARN);
	}

	private LogEntry infoEntry() {
		return new LogEntry(Level.INFO);
	}

	private LogSource sourceProducingEntries(LogEntry... entries) {
		return () -> Flux.just(entries);
	}

	private LogProcessor logProcessor() {
		return new ProjectReactorLogProcessor();
	}
}
