package pro.buildmysoftware.webflux.stacjait.conference.domainmodel;

import lombok.NonNull;
import lombok.Value;

@Value
public class UserLeftConference {

	@NonNull UserId user;
	@NonNull ConferenceId conference;
}
