package nextstep.sessions.domain;

public enum SessionStatus {
	READY("준비중"),
	OPENED("모집중"),
	CLOSED("종료");

	private final String description;

	SessionStatus(String description) {
		this.description = description;
	}

	public boolean isOpened() {
		return this == OPENED;
	}
}
