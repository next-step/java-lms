package nextstep.courses.domain.enums;

import java.util.Optional;

public enum SessionStatus {
	READY, RECRUITING, END;

	public boolean isStatusNotRecruiting() {
		return this != SessionStatus.RECRUITING;
	}

	public static Optional<SessionStatus> findBySessionStr(String sessionStr) {
		try {
			return Optional.of(SessionStatus.valueOf(sessionStr));
		} catch (Exception e) {
			return Optional.empty();
		}
	}
}
