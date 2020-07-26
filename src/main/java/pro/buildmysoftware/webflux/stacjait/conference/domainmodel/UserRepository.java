package pro.buildmysoftware.webflux.stacjait.conference.domainmodel;

import java.util.Optional;

public interface UserRepository {

	Optional<User> byId(UserId id);
}
