package nextstep.courses.error.exception;

import java.text.MessageFormat;
import nextstep.courses.domain.session.SessionType;

public class NotExistSessionType extends RuntimeException {

    public NotExistSessionType(SessionType sessionType) {
        super(MessageFormat.format("{0} 입력값: {1}", "존재하지 않는 강의 유형입니다.",
            sessionType));
    }
}
