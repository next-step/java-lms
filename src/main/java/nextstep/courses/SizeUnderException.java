package nextstep.courses;

public class SizeUnderException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public SizeUnderException(String message) {
        super(message);
    }
}
