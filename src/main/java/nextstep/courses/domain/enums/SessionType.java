package nextstep.courses.domain.enums;

import java.util.Arrays;
import java.util.Optional;

public enum SessionType {
	PAY("P"), FREE("F");

	private final String type;

	SessionType(String type) {
		this.type = type;
	}

	public static Optional<SessionType> findByTypeStr(String typeStr) {
		return Arrays.stream(SessionType.values())
			.filter(sessionType -> typeStr.equalsIgnoreCase(sessionType.type))
			.findFirst();
	}

	public static boolean isPaySession(String typeStr) {
		Optional<SessionType> sessionType = findByTypeStr(typeStr);
		return sessionType.filter(value -> value == SessionType.PAY).isPresent();
	}

	public String getType() {
		return type;
	}
}
