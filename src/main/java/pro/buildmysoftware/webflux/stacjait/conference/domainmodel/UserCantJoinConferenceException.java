package pro.buildmysoftware.webflux.stacjait.conference.domainmodel;

public class UserCantJoinConferenceException extends RuntimeException {

	public UserCantJoinConferenceException(String message) {
		super(message);
	}

	public UserCantJoinConferenceException(String message,
					       Throwable cause) {
		super(message, cause);
	}
}
