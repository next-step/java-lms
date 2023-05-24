package nextstep.courses.exception;

public class ExceedSessionCapacityException extends RuntimeException {

    public ExceedSessionCapacityException(String message) {
        super(message);
    }

    public ExceedSessionCapacityException() {
        this("최대 수강인원을 초과했습니다.");
    }
}
