package nextstep.courses.error.exception;

import java.text.MessageFormat;

public class NotExistSession extends RuntimeException {

    public NotExistSession(String sessionName) {
        super(MessageFormat.format("{0} 입력값: {1}", "코스에 등록되지 않은 강의입니다.",
            sessionName));
    }
}
