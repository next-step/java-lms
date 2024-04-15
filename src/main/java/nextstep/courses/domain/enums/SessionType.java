package nextstep.courses.domain.enums;

import java.util.Arrays;
import java.util.Optional;

public enum SessionType {
	PAY("P", "유료 강의"), FREE("F", "무료 강의");

	private final String type;
	private final String description;

	SessionType(String type, String description) {
		this.type = type;
		this.description = description;
	}

	public static Optional<SessionType> findByTypeStr(String typeStr) {
		return Arrays.stream(SessionType.values())
			.filter(sessionType -> typeStr.equalsIgnoreCase(sessionType.type))
			.findFirst();
	}

	public String getType() {
		return type;
	}
}
