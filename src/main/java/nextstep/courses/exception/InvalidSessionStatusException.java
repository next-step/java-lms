package nextstep.courses.exception;

public class InvalidSessionStatusException extends RuntimeException {

    public InvalidSessionStatusException(String code) {
        super("존재하지 않는 강의 상태입니다." + code);
    }
}
