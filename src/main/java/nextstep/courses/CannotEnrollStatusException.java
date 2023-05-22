package nextstep.courses;

public class CannotEnrollStatusException extends RuntimeException{
    public static final String MESSAGE = "수강신청 가능한 상태가 아닙니다.";

    public CannotEnrollStatusException() {
    }

    public CannotEnrollStatusException(String message) {
        super(message);
    }
}
