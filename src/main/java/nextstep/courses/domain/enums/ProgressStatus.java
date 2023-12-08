package nextstep.courses.domain.enums;

public enum ProgressStatus {
	READY,
	IN_PROGRESS,
	FINISH;

	public boolean isFinish() {
		return this == FINISH;
	}
}
