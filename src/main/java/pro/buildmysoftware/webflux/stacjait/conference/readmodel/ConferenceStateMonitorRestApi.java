package pro.buildmysoftware.webflux.stacjait.conference.readmodel;

import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stacjait/conference/{id}/events")
public class ConferenceStateMonitorRestApi {

	private ConferenceStateMonitor conferenceStateMonitor;

	public ConferenceStateMonitorRestApi(ConferenceStateMonitor conferenceStateMonitor) {
		this.conferenceStateMonitor = conferenceStateMonitor;
	}

	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Publisher<UserEvent> events() {
		return conferenceStateMonitor.userEvents();
	}
}
