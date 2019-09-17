package pro.buildmysoftware.webflux.logworkshop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import pro.buildmysoftware.webflux.log.LogEntry;
import reactor.test.StepVerifier;

import java.util.List;

public class LogMessageExtractorTest {
	// @formatter:off
	@DisplayName(
		"should extract messages only of INFO log entries"
	)
	// @formatter:on
	@Test
	void test() throws Exception {
		// given
		LogEntry firstEntry = new LogEntry("INFO", "msg");
		LogEntry secondEntry = new LogEntry("DEBUG", "msg 2");
		LogSource logSource = logSource(firstEntry, secondEntry);
		LogMessageExtractor extractor = new SimpleMessageExtractor();

		// when
		Publisher<String> extractedEntriesPublisher = extractor
			.extractMsgOfLevel("INFO", logSource);

		// then
		StepVerifier.create(extractedEntriesPublisher)
			.expectSubscription().expectNext("msg")
			.verifyComplete();
	}

	private LogSource logSource(LogEntry... entries) {
		return new DummyLogSource(List.of(entries));
	}
}
