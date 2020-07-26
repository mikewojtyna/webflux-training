package pro.buildmysoftware.webflux.stacjait.conference.readmodel;

import lombok.NonNull;
import lombok.Value;

@Value
public class UserJoinedRoom implements UserEvent {

	@NonNull String room;
	@NonNull String user;
	@NonNull State state;

	@Override
	public State state() {
		return state;
	}
}
