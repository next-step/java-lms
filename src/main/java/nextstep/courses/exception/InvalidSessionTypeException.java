package nextstep.courses.exception;

public class InvalidSessionTypeException extends RuntimeException {

    public InvalidSessionTypeException(String code) {
        super("존재하지 않는 강의 코드입니다." + code);
    }
}
