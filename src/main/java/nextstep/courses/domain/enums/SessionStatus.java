package nextstep.courses.domain.enums;

public enum SessionStatus {
	READY, RECRUITING, END;

	public boolean isStatusNotRecruiting() {
		return this != SessionStatus.RECRUITING;
	}
}
