package nextstep.courses.exception;

public class InvalidImageWidthAndHeightException extends RuntimeException {
    public InvalidImageWidthAndHeightException() {
        super("이미지의 width는 300픽셀, height는 200픽셀 이상, width와 height의 비율은 3:2여야 합니다.");
    }
}
