package nextstep.courses.exception;

public class InvalidSessionException extends RuntimeException {
    public InvalidSessionException() {
        super("id, 커버이미지, 시작일, 종료일은 필수값입니다.");
    }
}
