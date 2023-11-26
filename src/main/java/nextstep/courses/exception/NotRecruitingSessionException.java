package nextstep.courses.exception;

public class NotRecruitingSessionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotRecruitingSessionException(String message) {
        super(message);
    }
}
