package nextstep.courses.domain.enums;

public enum Status {
	READY,
	APPLYING,
	END;

	public boolean isApplying() {
		return this == APPLYING;
	}
}
