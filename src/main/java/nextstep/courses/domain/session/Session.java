package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImages;
import nextstep.courses.domain.session.enroll.RecruitingStatus;
import nextstep.courses.domain.session.period.Period;
import nextstep.courses.domain.session.student.SelectionStatus;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.domain.session.student.SessionStudents;
import nextstep.courses.dto.EnrolmentInfo;

import java.util.Objects;

public abstract class Session {

    protected Long id;
    protected PayType payType;
    protected SessionStatus sessionStatus;
    protected RecruitingStatus recruitingStatus;
    protected CoverImages coverImages;
    protected SessionStudents sessionStudents;
    protected Period period;

    public Session(Long id, PayType payType, SessionStatus sessionStatus, RecruitingStatus recruitingStatus, CoverImages coverImages, SessionStudents sessionStudents, Period period) {
        this.id = id;
        this.payType = payType;
        this.sessionStatus = sessionStatus;
        this.recruitingStatus = recruitingStatus;
        this.coverImages = coverImages;
        this.sessionStudents = sessionStudents;
        this.period = period;
    }

    public abstract SessionStudent enroll(EnrolmentInfo enrolmentInfo);

    public SessionStudent selection(SessionStudent student, SelectionStatus selectionStatus) {
        return sessionStudents.selectStudent(student, selectionStatus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && payType == session.payType && sessionStatus == session.sessionStatus && recruitingStatus == session.recruitingStatus && Objects.equals(coverImages, session.coverImages) && Objects.equals(sessionStudents, session.sessionStudents) && Objects.equals(period, session.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, payType, sessionStatus, recruitingStatus, coverImages, sessionStudents, period);
    }
}
