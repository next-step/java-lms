package nextstep.sessions;

public class SessionNotFoundException extends RuntimeException {

    public SessionNotFoundException(Long id) {
        super(String.format("%s와 일치하는 Session을 찾지 못했습니다.", id));
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
