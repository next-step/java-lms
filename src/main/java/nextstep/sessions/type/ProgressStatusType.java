package nextstep.sessions.type;

import java.util.Arrays;

public enum ProgressStatusType {

	PREPARING("준비중"),
	IN_PROGRESS("진행중"),
	TERMINATION("종료");

	private final String message;

	ProgressStatusType(String message) {
		this.message = message;
	}

	public static ProgressStatusType of(ProgressStatusType progressStatusType) {
		return Arrays.stream(values())
			.filter(type -> type.equals(progressStatusType))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 진행 상태 타입입니다."));
	}

	public boolean isProgressing() {
		return this == IN_PROGRESS;
	}
}
