package pro.buildmysoftware.webflux.stacjait.log;

import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stacjait/logs")
public class LogProcessorRestController {

	private LogSource logSource;
	private LogProcessor logProcessor;

	public LogProcessorRestController(LogSource logSource,
					  LogProcessor logProcessor) {
		this.logSource = logSource;
		this.logProcessor = logProcessor;
	}

	@GetMapping
	public Publisher<LogEntry> warnLogs() {
		return logProcessor
			.filter(logSource, logEntry -> logEntry.getLevel()
				.equals(Level.WARN));
	}
}
