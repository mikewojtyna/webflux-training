package pro.buildmysoftware.webflux.stacjait.conference.domainmodel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

	@NonNull
	private UserId id;

	public UserId id() {
		return id;
	}
}
