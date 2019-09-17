package pro.buildmysoftware.webflux.log;

import lombok.Value;

@Value
public class LogEntry {
	private final String level;
	private final String msg;
}
