package nextstep.image.exception;

public class EmptyImageUrlException extends IllegalArgumentException {
    public EmptyImageUrlException() {
        super("ImageUrl 은 비어있을수 없습니다");
    }
}
