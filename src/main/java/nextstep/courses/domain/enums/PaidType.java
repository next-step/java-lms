package nextstep.courses.domain.enums;

public enum PaidType {
	FREE("무료"),
	PAID("유료")
	;

	private final String description;

	PaidType(String description) {
		this.description = description;
	}

	public boolean isFree() {
		return this == FREE;
	}
}
