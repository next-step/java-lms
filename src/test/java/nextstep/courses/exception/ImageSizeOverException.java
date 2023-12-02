package nextstep.courses.exception;

public class ImageSizeOverException extends RuntimeException {
    public ImageSizeOverException(long size) {
        super("이미지 크기는 1MB를 초과할 수 없습니다. size : " + size);
    }
}
