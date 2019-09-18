package pro.buildmysoftware.webflux.workshop.events;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class SpringDelegatingEventSourceIntegrationTest {
	@MockBean
	private ReactiveEventSource eventSource;
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	// @formatter:off
	@DisplayName(
		"when publish event, " +
		"then event handler is notified about this"
	)
	// @formatter:on
	@Test
	void test() throws Exception {
		// given
		Event event = new Event("event");

		// when
		eventPublisher.publishEvent(event);

		// then
		verify(eventSource, times(1)).handle(event);
	}
}
