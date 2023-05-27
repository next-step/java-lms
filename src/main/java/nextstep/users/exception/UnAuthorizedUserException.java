package nextstep.users.exception;

public class UnAuthorizedUserException extends RuntimeException {
    public UnAuthorizedUserException() {
        super("잘못된 유저 정보입니다");
    }
}
