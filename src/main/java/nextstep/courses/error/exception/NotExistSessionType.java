package nextstep.courses.error.exception;

import java.text.MessageFormat;

public class NotExistSessionType extends RuntimeException {

    public NotExistSessionType() {
        super(MessageFormat.format("{0}", "존재하지 않는 강의 유형입니다"));
    }
}
