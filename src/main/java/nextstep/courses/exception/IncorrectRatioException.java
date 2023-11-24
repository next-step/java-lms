package nextstep.courses.exception;

public class IncorrectRatioException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public IncorrectRatioException(String message) {
        super(message);
    }
}
