package nextstep.courses.error.exception;

import java.text.MessageFormat;

public class NotExistSession extends RuntimeException {

    public NotExistSession(Long sessionId) {
        super(MessageFormat.format("{0} 입력값: {1}", "존재하지 않는 강의입니다.",
            sessionId));
    }
}
