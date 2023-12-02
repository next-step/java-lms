package nextstep.courses.exception;

public class InvalidImageExtensionException extends RuntimeException {
    public InvalidImageExtensionException() {
        super("gif, jpg(jpeg 포함), png, svg 확장자만 허용됩니다.");
    }
}
