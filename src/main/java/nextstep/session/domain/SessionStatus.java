package nextstep.session.domain;

import java.util.Arrays;

public enum SessionStatus {
	PREPARING(0),
	PROCEEDING(1),
	CLOSED(2);

	private int statusValue;

	SessionStatus(int statusValue) {
		this.statusValue = statusValue;
	}

	public static SessionStatus getSessionStatus(int value) {
		return Arrays.stream(SessionStatus.values())
				.filter(v -> v.statusValue == value)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("해당 value와 매칭되는 값이 없습니다."));
	}

	public int getStatusValue() {
		return statusValue;
	}

}
