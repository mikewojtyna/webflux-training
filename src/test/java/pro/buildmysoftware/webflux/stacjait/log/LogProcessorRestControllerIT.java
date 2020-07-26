package pro.buildmysoftware.webflux.stacjait.log;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureWebTestClient
class LogProcessorRestControllerIT {

	@Autowired
	private WebTestClient webTestClient;

	// @formatter:off
	@DisplayName(
		"when GET on /api/stacjait/logs, " +
		"then receive only 3 warn messages"
	)
	//@formatter:on
	@Test
	void test() throws Exception {
		// when
		Flux<LogEntry> response = webTestClient.get()
			.uri("/api/stacjait/logs").exchange()
			.returnResult(LogEntry.class).getResponseBody();

		// then
		StepVerifier.create(response)
			.assertNext(logEntry -> assertThat(logEntry.getLevel())
				.isEqualTo(Level.WARN))
			.assertNext(logEntry -> assertThat(logEntry.getLevel())
				.isEqualTo(Level.WARN))
			.assertNext(logEntry -> assertThat(logEntry.getLevel())
				.isEqualTo(Level.WARN)).thenCancel()
			.verify(Duration.ofSeconds(10));
	}
}
