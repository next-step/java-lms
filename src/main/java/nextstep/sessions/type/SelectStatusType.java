package nextstep.sessions.type;

public enum SelectStatusType {

	UNDER_REVIEW("심사중"),
	SELECTED("선발됨"),
	NOT_SELECTED("선발되지 않음");

	private final String message;

	SelectStatusType(String message) {
		this.message = message;
	}

	public boolean isNotSelected() {
		return this == NOT_SELECTED;
	}
}
