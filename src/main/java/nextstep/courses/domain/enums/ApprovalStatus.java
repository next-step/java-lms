package nextstep.courses.domain.enums;

public enum ApprovalStatus {
	WAITING,
	APPROVAL,
	CANCEL
	;

	public boolean isApproval() {
		return this == APPROVAL;
	}
}
