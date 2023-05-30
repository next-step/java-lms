package nextstep.courses.exception;

public class SessionRegisterException extends RuntimeException {

    public SessionRegisterException() {
        super("수강등록에 실패하였습니다.");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
