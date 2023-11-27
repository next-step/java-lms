package nextstep.courses.exception;

public class ImageVolumeOverException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ImageVolumeOverException(String message) {
        super(message);
    }
}
