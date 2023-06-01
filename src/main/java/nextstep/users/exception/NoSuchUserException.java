package nextstep.users.exception;

public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException(String message) {
        super(message);
    }

    public NoSuchUserException() {
        this("해당 회원이 존재하지 않습니다.");
    }
}
