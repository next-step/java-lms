package nextstep.exception;

public class AlreadyEnrollException extends RuntimeException {

    private static final String message = "이미 등록된 유저입니다.";

    public AlreadyEnrollException() {
        super(message);
    }
}
