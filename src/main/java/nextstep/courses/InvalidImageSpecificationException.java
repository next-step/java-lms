package nextstep.courses;

public class InvalidImageSpecificationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidImageSpecificationException(String message) {
        super(message);
    }
}
