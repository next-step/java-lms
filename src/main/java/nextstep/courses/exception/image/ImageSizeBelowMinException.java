package nextstep.courses.exception.image;

public class ImageSizeBelowMinException extends RuntimeException{
    public ImageSizeBelowMinException(String message) {
        super(message);
    }
}
