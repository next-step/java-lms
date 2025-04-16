package nextstep.session.domain.exception;

public class InvalidImageSizeException extends RuntimeException {
    public InvalidImageSizeException(int width, int height) {
        super("이미지의 최소 크기는 300x200입니다. 비율은 3:2여야 합니다."
                + "넓이:" + width
                + ",높이:" + height
                + ",비율:" + ((double) width / height)
        );
    }
}
