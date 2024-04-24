package nextstep.courses.error.exception;

import java.text.MessageFormat;

public class NotExistEnrollmentCountType extends RuntimeException {

    public NotExistEnrollmentCountType() {
        super(MessageFormat.format("{0}", "존재하지 않는 등록수 유형입니다"));
    }
}
