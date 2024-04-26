package nextstep.courses.error.exception;

import java.text.MessageFormat;
import nextstep.courses.domain.session.enrollment.count.RegistrationCount;
import nextstep.courses.domain.session.enrollment.count.engine.EnrollmentCount;

public class MaxRegistrationExceededException extends RuntimeException {

    public MaxRegistrationExceededException(EnrollmentCount enrollmentCount) {
        super(MessageFormat.format("{0} 현재 강의 등록 인원: {1}",
            "최대 등록 인원수를 초과하였습니다."
            , enrollmentCount.getRegistrationCount()));
    }

    public MaxRegistrationExceededException(RegistrationCount registrationCount) {
        super(MessageFormat.format("{0} 현재 강의 등록 인원: {1}",
            "최대 등록 인원수를 초과하였습니다."
            , registrationCount.getValue()));
    }
}
