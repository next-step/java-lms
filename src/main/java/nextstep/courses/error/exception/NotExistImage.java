package nextstep.courses.error.exception;

import java.text.MessageFormat;

public class NotExistImage extends RuntimeException {

    public NotExistImage(Long sessionId) {
        super(MessageFormat.format("{0} 입력값: {1}", "존재하지 않는 이미지입니다.",
            sessionId));
    }
}
