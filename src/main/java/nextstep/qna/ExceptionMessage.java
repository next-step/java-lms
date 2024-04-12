package nextstep.qna;

public enum ExceptionMessage {
    NO_AUTHORITY_TO_DELETE_ANSWER("답변을 삭제할 권한이 없습니다."),
    DIFFERENT_WRITER_OF_QUESTION_AND_WRITER_OF_ANSWER("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
