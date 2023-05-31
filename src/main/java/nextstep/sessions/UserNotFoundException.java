package nextstep.sessions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("일치하는 사용자를 찾을 수 없습니다.");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
