package nextstep.courses.error.exception;

import java.text.MessageFormat;

public class ImageHeightSizeException extends RuntimeException {

    public ImageHeightSizeException(int imageSize) {
        super(MessageFormat.format("{0} 입력값: {1}", "높이 사이즈는 200이상이어야 합니다",
            imageSize));
    }
}
