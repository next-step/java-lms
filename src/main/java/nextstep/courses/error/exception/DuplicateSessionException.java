package nextstep.courses.error.exception;

import java.text.MessageFormat;
import nextstep.courses.domain.session.SessionName;

public class DuplicateSessionException extends RuntimeException {

    public DuplicateSessionException(SessionName sessionName) {
        super(MessageFormat.format("{0} 입력값: {1}", "이미 등록된 강의입니다",
            sessionName.getValue()));
    }
}
