package nextstep.courses;

public class InvalidImageDimensionException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidImageDimensionException(String message) {
        super(message);
    }
}
