package pro.buildmysoftware.webflux.log;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Assertions.assertThat;

public class LogSourceTest {
	// @formatter:off
	@DisplayName(
		"when log source produces logs for 2 seconds, " +
		"then only two logs are produced"
	)
	// @formatter:on
	@Test
	void test() throws Exception {
		// given
		LogSource logSource = new LogSource();

		// when
		StepVerifier
			.withVirtualTime(() -> Flux.from(logSource.logEntries())
				.take(2))

			// then
			.expectSubscription().expectNoEvent(ofSeconds(1))
			.assertNext(l -> assertThat(l.getMsg())
				.isEqualTo("Msg 0")).expectNoEvent(ofSeconds(1))
			.assertNext(l -> assertThat(l.getMsg())
				.isEqualTo("Msg 1")).verifyComplete();
	}
}
