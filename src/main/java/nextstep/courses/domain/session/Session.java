package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImages;
import nextstep.courses.domain.session.enroll.Enrolment;
import nextstep.courses.domain.session.student.SelectionStatus;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.dto.EnrolmentInfo;

import java.time.LocalDate;
import java.util.Objects;

import static nextstep.courses.domain.session.SessionStatus.isNotProgressing;

public abstract class Session {

    protected Long id;
    protected PayType payType;
    protected SessionStatus sessionStatus;
    protected CoverImages coverImages;
    protected Enrolment enrolment;
    protected LocalDate startDate;
    protected LocalDate endDate;

    public Session(Long id, PayType payType, SessionStatus sessionStatus, CoverImages coverImages, Enrolment enrolment, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.payType = payType;
        this.sessionStatus = sessionStatus;
        this.coverImages = coverImages;
        this.enrolment = enrolment;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public abstract SessionStudent enroll(EnrolmentInfo enrolmentInfo);

    protected void validateSessionStatus() {
        if (isNotProgressing(sessionStatus)) {
            throw new IllegalArgumentException(String.format("해당 강의는 현재 %s입니다.", sessionStatus.description()));
        }
    }

    public SessionStudent selection(SessionStudent student, SelectionStatus selectionStatus) {
        return enrolment.selection(student, selectionStatus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && payType == session.payType && sessionStatus == session.sessionStatus && Objects.equals(coverImages, session.coverImages) && Objects.equals(enrolment, session.enrolment) && Objects.equals(startDate, session.startDate) && Objects.equals(endDate, session.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, payType, sessionStatus, coverImages, enrolment, startDate, endDate);
    }
}
