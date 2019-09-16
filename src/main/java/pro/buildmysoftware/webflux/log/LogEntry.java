package pro.buildmysoftware.webflux.log;

import lombok.Value;

@Value
public class LogEntry {
	private final String category;
	private final String msg;
}
