package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImages;
import nextstep.courses.domain.session.enroll.Enrolment;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.dto.EnrolmentInfo;

import java.time.LocalDate;

public class FreeSession extends Session {

    public FreeSession(Long id, PayType payType, SessionStatus sessionStatus, CoverImages coverImages, Enrolment enrolment, LocalDate startDate, LocalDate endDate) {
        super(id, payType, sessionStatus, coverImages, enrolment, startDate, endDate);
    }

    @Override
    public SessionStudent enroll(EnrolmentInfo enrolmentInfo) {
        validateSessionStatus();

        return enrolment.enroll(enrolmentInfo);
    }
}
