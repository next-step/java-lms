package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.enroll.Enrolment;
import nextstep.courses.domain.session.student.Student;
import nextstep.courses.dto.EnrolmentInfo;

import java.time.LocalDate;

public class FreeSession extends Session {

    public FreeSession(Long id, PayType payType, SessionStatus sessionStatus, CoverImage coverImage, Enrolment enrolment, LocalDate startDate, LocalDate endDate) {
        super(id, payType, sessionStatus, coverImage, enrolment, startDate, endDate);
    }

    @Override
    public Student enroll(EnrolmentInfo enrolmentInfo) {
        validateSessionStatus();

        return enrolment.enroll(enrolmentInfo);
    }
}
