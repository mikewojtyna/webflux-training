package pro.buildmysoftware.webflux.stacjait.conference.domainmodel;

import lombok.NonNull;
import lombok.Value;

import java.util.Collection;

@Value
public class UserJoinedConference {

	@NonNull RoomId room;
	@NonNull UserId user;
	@NonNull ConferenceId conference;
	@NonNull Collection<UserId> allUsers;
}
