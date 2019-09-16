package pro.buildmysoftware.webflux.log;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogConsumerTest {
	// @formatter:off
	@DisplayName(
		"should extract categories from log entries"
	)
	// @formatter:on
	@Test
	void test() throws Exception {
		// given
		LogSource logSource = mock(LogSource.class);
		TestPublisher<LogEntry> testPublisher = TestPublisher.create();
		when(logSource.logEntries()).thenReturn(testPublisher);
		LogConsumer logConsumer = new LogConsumer(logSource);

		// when
		StepVerifier.create(logConsumer.extractCategories())

			// then
			.then(() -> testPublisher
				.emit(logEntryOfCategory("A"),
					logEntryOfCategory("B"),
					logEntryOfCategory("A")))
			.expectNext("A").expectNext("B").expectNext("A")
			.verifyComplete();
	}

	private LogEntry logEntryOfCategory(String category) {
		return new LogEntry(category, "msg");
	}
}
