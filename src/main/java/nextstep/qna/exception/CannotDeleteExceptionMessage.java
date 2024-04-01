package nextstep.qna.exception;

public enum CannotDeleteExceptionMessage {

    CAN_DELETE_ONLY_ANSWER_OWNER("자신이 작성한 답변만 삭제할 수 있습니다."),
    CAN_DELETE_ONLY_QUESTION_OWNER("자식이 작성한 질문막 삭제할 수 있습니다.");

    private final String message;

    CannotDeleteExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
