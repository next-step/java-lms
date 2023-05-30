package nextstep.sessions.type;

import java.util.Arrays;

public enum RecruitStatusType {

	RECRUITING("모집중"),
	NOT_RECRUITING("비모집");

	private final String message;

	RecruitStatusType(String message) {
		this.message = message;
	}

	public static RecruitStatusType of(RecruitStatusType recruitStatusType) {
		return Arrays.stream(values())
			.filter(type -> type.equals(recruitStatusType))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 모집 상태 타입입니다."));
	}

	public boolean isRecruiting() {
		return this == RecruitStatusType.RECRUITING;
	}
}
