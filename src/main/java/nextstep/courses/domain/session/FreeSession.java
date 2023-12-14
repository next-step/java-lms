package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImages;
import nextstep.courses.domain.session.enroll.Enrolment;
import nextstep.courses.domain.session.period.Period;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.domain.session.student.SessionStudents;
import nextstep.courses.dto.EnrolmentInfo;

public class FreeSession extends Session {

    public FreeSession(Long id, PayType payType, SessionStatus sessionStatus, CoverImages coverImages, Enrolment enrolment, SessionStudents sessionStudents, Period period) {
        super(id, payType, sessionStatus, coverImages, enrolment, sessionStudents, period);
    }

    @Override
    public SessionStudent enroll(EnrolmentInfo enrolmentInfo) {
        validateSessionStatus();

        return enrolment.enroll(enrolmentInfo);
    }
}
