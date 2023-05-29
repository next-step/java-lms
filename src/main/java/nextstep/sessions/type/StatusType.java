package nextstep.sessions.type;

import java.util.Arrays;

public enum StatusType {

	PREPARING("준비중"),
	RECRUITING("모집중"),
	TERMINATION("종료");

	private final String message;

	StatusType(String message) {
		this.message = message;
	}

	public static StatusType of(StatusType statusType) {
		return Arrays.stream(values())
			.filter(type -> type.equals(statusType))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상태 타입입니다."));
	}

	public boolean isRecruiting() {
		return this == StatusType.RECRUITING;
	}
}
