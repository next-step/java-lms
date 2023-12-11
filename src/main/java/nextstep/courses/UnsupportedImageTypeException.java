package nextstep.courses;

public class UnsupportedImageTypeException extends Exception {
    private static final long serialVersionUID = 1L;

    public UnsupportedImageTypeException(String message) {
        super(message);
    }
}
