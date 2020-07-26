package pro.buildmysoftware.webflux.stacjait.log;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import lombok.Value;

@Value
public class LogEntry {

	@NonNull Level level;

	@JsonCreator
	public LogEntry(@JsonProperty("level") @NonNull Level level) {
		this.level = level;
	}
}
