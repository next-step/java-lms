package nextstep.image.exception;

public class InvalidImageUrlException extends IllegalArgumentException {
    public InvalidImageUrlException() {
        super("ImageUrl 이 유효하지 않습니다");
    }
}
