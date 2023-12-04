package nextstep.courses.exception.image;

public class ImageFileSizeExceededException extends RuntimeException{
    public ImageFileSizeExceededException(String message) {
        super(message);
    }
}
