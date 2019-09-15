package pro.buildmysoftware.webflux.handler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
public class HelloHandlerIntegrationTest {
	@Autowired
	private WebTestClient webTestClient;

	// @formatter:off
	@DisplayName(
		"when GET on /hello, " +
		"then hello message with status 200 is returned"
	)
	// @formatter:on
	@Test
	void test() throws Exception {
		// when
		webTestClient.get().uri("/hello").exchange()
			// then
			.expectStatus().isOk().expectBody().jsonPath("$.msg")
			.isEqualTo("hello");
	}
}
