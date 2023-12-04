package nextstep.courses.domain.enums;

public enum PaidType {
	FREE,
	PAID
	;

	public boolean isFree() {
		return this == FREE;
	}
}
