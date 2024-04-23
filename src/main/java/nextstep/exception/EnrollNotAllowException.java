package nextstep.exception;

public class EnrollNotAllowException extends RuntimeException {

    private static final String message = "정원을 초과하여, 강의 신청을 할 수 없습니다.";

    public EnrollNotAllowException() {
        super(message);
    }
}
