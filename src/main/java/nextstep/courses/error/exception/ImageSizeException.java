package nextstep.courses.error.exception;

import java.text.MessageFormat;

public class ImageSizeException extends RuntimeException {

    public ImageSizeException(int imageSize) {
        super(MessageFormat.format("{0} 입력값: {1}", "이미지 사이즈의 최대 크기는 1입니다",
            imageSize));
    }
}
