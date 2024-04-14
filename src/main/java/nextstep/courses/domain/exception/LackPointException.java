package nextstep.courses.domain.exception;

public class LackPointException extends RuntimeException{
    private static final String ERROR_MESSAGE = "포인트가 부족합니다.";

    public LackPointException() {
        super(ERROR_MESSAGE);
    }
}
