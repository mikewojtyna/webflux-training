package pro.buildmysoftware.webflux.logworkshop;

import org.reactivestreams.Publisher;

public interface LogMessageExtractor {
	Publisher<String> extractMsgOfLevel(String level, LogSource logSource);
}
