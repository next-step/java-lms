package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImages;
import nextstep.courses.domain.session.enroll.Enrolment;
import nextstep.courses.domain.session.enroll.RecruitingStatus;
import nextstep.courses.domain.session.period.Period;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.domain.session.student.SessionStudents;
import nextstep.courses.dto.EnrolmentInfo;

public class PaySession extends Session {

    private Long amount;
    private int studentsCapacity;

    public PaySession(Long id, PayType payType, SessionStatus sessionStatus, RecruitingStatus recruitingStatus, CoverImages coverImages,
                      Enrolment enrolment, SessionStudents sessionStudents, Period period, Long amount, int studentsCapacity) {
        super(id, payType, sessionStatus, recruitingStatus, coverImages, enrolment, sessionStudents, period);
        this.amount = amount;
        this.studentsCapacity = studentsCapacity;
    }

    @Override
    public SessionStudent enroll(EnrolmentInfo enrolmentInfo) {
        Enrolment enrolment = Enrolment.fromPaySession(sessionStudents, sessionStatus, recruitingStatus, amount, studentsCapacity);

        return enrolment.enroll(enrolmentInfo);
    }
}
