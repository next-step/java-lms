package nextstep.qna.exception;

public enum QnAExceptionCode {

    NOT_EXIST_AUTHENTICATION("삭제할 권한이 없습니다."),
    NOT_EXIST_QUESTION("이미 삭제된 질문입니다.");

    private final String message;

    QnAExceptionCode(String message) {
        this.message = message;
    }

    public String message() {
        return this.message;
    }
}
