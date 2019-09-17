package pro.buildmysoftware.webflux.workshop.log;

import org.reactivestreams.Publisher;

public interface LogMessageExtractor {
	Publisher<String> extractMsgOfLevel(String level, LogSource logSource);
}
