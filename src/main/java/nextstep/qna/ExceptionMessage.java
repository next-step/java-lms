package nextstep.qna;

public enum ExceptionMessage {
	NO_AUTHORITY_TO_DELETE_ANSWER("답변을 삭제할 권한이 없습니다."),
	NO_AUTHORITY_TO_DELETE_QUESTION("질문을 삭제할 권한이 없습니다.");

	private final String message;

	ExceptionMessage(String message) {
		this.message = message;
	}

	public String message() {
		return message;
	}
}