package nextstep.courses.exception;

public class ImageOverVolumeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ImageOverVolumeException(String message) {
        super(message);
    }
}
