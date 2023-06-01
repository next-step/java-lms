package nextstep.users.exception;

public class NotOwnedSessionException extends RuntimeException {

	public NotOwnedSessionException(String message) {
		super(message);
	}
}
