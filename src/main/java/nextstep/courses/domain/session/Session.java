package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImages;
import nextstep.courses.domain.session.enroll.Enrolment;
import nextstep.courses.domain.session.enroll.RecruitingStatus;
import nextstep.courses.domain.session.period.Period;
import nextstep.courses.domain.session.student.SelectionStatus;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.domain.session.student.SessionStudents;
import nextstep.courses.dto.EnrolmentInfo;

import java.util.Objects;

import static nextstep.courses.domain.session.SessionStatus.isNotProgressing;

public abstract class Session {

    protected Long id;
    protected PayType payType;
    protected SessionStatus sessionStatus;
    protected RecruitingStatus recruitingStatus;
    protected CoverImages coverImages;
    protected Enrolment enrolment;
    protected SessionStudents sessionStudents;
    protected Period period;

    public Session(Long id, PayType payType, SessionStatus sessionStatus, RecruitingStatus recruitingStatus, CoverImages coverImages, Enrolment enrolment, SessionStudents sessionStudents, Period period) {
        this.id = id;
        this.payType = payType;
        this.sessionStatus = sessionStatus;
        this.recruitingStatus = recruitingStatus;
        this.coverImages = coverImages;
        this.enrolment = enrolment;
        this.sessionStudents = sessionStudents;
        this.period = period;
    }

    public abstract SessionStudent enroll(EnrolmentInfo enrolmentInfo);

    public SessionStudent selection(SessionStudent student, SelectionStatus selectionStatus) {
        return sessionStudents.selectStudent(student, selectionStatus);
    }

    protected void validateSessionStatus() {
        if (isNotProgressing(sessionStatus)) {
            throw new IllegalArgumentException(String.format("해당 강의는 현재 %s입니다.", sessionStatus.description()));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && payType == session.payType && sessionStatus == session.sessionStatus && Objects.equals(coverImages, session.coverImages) && Objects.equals(enrolment, session.enrolment) && Objects.equals(sessionStudents, session.sessionStudents) && Objects.equals(period, session.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, payType, sessionStatus, coverImages, enrolment, sessionStudents, period);
    }
}
