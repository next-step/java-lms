package nextstep.session.domain;

public enum SessionStatus {
	PREPARING(0),
	PROCEEDING(1),
	CLOSED(2);

	private int statusValue;

	SessionStatus(int statusValue) {
		this.statusValue = statusValue;
	}

	public int getStatusValue() {
		return statusValue;
	}

}
