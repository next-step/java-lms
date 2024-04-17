package nextstep.courses.error.exception;

import java.text.MessageFormat;

public class ImageWidthSizeException extends RuntimeException {

    public ImageWidthSizeException(int imageSize) {
        super(MessageFormat.format("{0} 입력값: {1}", "가로 사이즈는 300이상 이어야 합니다",
            imageSize));
    }
}
