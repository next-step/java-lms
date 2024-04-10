package nextstep.courses.error.exception;

import java.text.MessageFormat;

public class SessionNameEmptyException extends RuntimeException {

    public SessionNameEmptyException(String sessionName) {
        super(MessageFormat.format("{0} 입력값: {1}", "강의 이름이 입력되지 않았습니다",
            sessionName));
    }
}
