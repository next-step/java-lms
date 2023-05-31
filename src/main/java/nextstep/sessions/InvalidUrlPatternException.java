package nextstep.sessions;

public class InvalidUrlPatternException extends RuntimeException {

    public InvalidUrlPatternException() {
        super("URL 패턴이 유효하지 않습니다.");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
