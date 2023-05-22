package nextstep.qna.exception;

public enum Exceptions {

    NOT_EXIST_AUTHENTICATION("삭제할 권한이 없습니다."),
    NOT_EXIST_QUESTION("이미 삭제된 질문입니다.");

    private final String message;

    Exceptions(String message) {
        this.message = message;
    }

    public String message() {
        return this.message;
    }
}
