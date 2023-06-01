package nextstep.sessions.type;

import java.util.Arrays;

public enum ProgressType {

	PREPARING("준비중"),
	IN_PROGRESS("진행중"),
	TERMINATION("종료");

	private final String message;

	ProgressType(String message) {
		this.message = message;
	}

	public static ProgressType of(ProgressType progressType) {
		return Arrays.stream(values())
			.filter(type -> type.equals(progressType))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 진행 상태 타입입니다."));
	}

	public boolean isProgressing() {
		return this == IN_PROGRESS;
	}
}
