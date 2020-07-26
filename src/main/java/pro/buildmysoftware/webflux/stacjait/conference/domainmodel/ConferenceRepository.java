package pro.buildmysoftware.webflux.stacjait.conference.domainmodel;

import java.util.Optional;

public interface ConferenceRepository {

	Optional<Conference> byId(ConferenceId id);

	void save(Conference conference);
}
