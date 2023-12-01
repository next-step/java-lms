package nextstep.courses.domain.enums;

public enum Status {
	READY("준비중"),
	APPLYING("모집중"),
	END("모집 종료");

	private final String description;

	Status(String description) {
		this.description = description;
	}

	public boolean isApplying() {
		return this == APPLYING;
	}
}
