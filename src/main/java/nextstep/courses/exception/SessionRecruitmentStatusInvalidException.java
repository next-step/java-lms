package nextstep.courses.exception;

import java.text.MessageFormat;

import static nextstep.courses.exception.CourseExceptionMessage.INVALID_SESSION_RECRUITMENT_STATUS;

public class SessionRecruitmentStatusInvalidException  extends CourseException {

    public SessionRecruitmentStatusInvalidException(String status) {
        super(INVALID_SESSION_RECRUITMENT_STATUS, MessageFormat.format("입력된 상태: {0}", status));
    }

}
