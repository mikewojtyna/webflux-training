package pro.buildmysoftware.webflux.stacjait.conference.domainmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Conference {

	private ConferenceId id;
	private List<UserId> users;

	public Conference(ConferenceId id) {
		this.id = id;
		users = new ArrayList<>();
	}

	public UserJoinedConference join(User user) throws UserCantJoinConferenceException {
		users.add(user.id());
		// TODO: add some business logic, e.g. when conference is
		//  full, or user first should join to the breakout room etc.
		return new UserJoinedConference(new RoomId(UUID.randomUUID()
			.toString()), user.id(), id, users);
	}

	public UserLeftConference leave(User user) throws UserCantJoinConferenceException {
		// TODO: add some business logic, e.g. when leave is for user
		//  who's not in the conference
		return new UserLeftConference(user.id(), id);
	}

	public ConferenceId id() {
		return id;
	}
}
