package nextstep.session.domain.exception;

public class InvalidImageFileSizeException extends RuntimeException {
    public InvalidImageFileSizeException(long size) {
        super("이미지의 크기는 0이상, 1MB 이하여야 합니다. 사이즈=" + size);
    }
}
