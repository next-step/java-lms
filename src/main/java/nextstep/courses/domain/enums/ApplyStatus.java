package nextstep.courses.domain.enums;

public enum ApplyStatus {
	APPLYING,
	CLOSED
	;

	public boolean isApplying() {
		return this == APPLYING;
	}
}
