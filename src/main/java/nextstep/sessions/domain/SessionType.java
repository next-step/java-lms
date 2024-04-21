package nextstep.sessions.domain;

public enum SessionType {
	FREE("무료강의"),
	PAID("유료강의");

	private final String description;

	SessionType(String description) {
		this.description = description;
	}
}
