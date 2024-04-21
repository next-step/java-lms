package nextstep.sessions.domain;

public enum ChargeStatus {
	FREE("무료강의"),
	PAID("유료강의");

	private final String description;

	ChargeStatus(String description) {
		this.description = description;
	}

	public void valid(int price) {
		if (isFree() && price > 0) {
			throw new IllegalArgumentException("무료 강의의 가격은 0이어야 합니다.");
		}
	}

	private boolean isFree() {
		return this == FREE;
	}
}
