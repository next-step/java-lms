package nextstep.courses;

public class MaxImageSizeExceededException extends Exception {
    private static final long serialVersionUID = 1L;

    public MaxImageSizeExceededException(String message) {
        super(message);
    }
}
