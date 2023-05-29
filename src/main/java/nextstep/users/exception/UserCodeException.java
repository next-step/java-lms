package nextstep.users.exception;

public class UserCodeException extends RuntimeException {
    public UserCodeException() {
        super("UserCode 는 비어있을수 없습니다");
    }
}
