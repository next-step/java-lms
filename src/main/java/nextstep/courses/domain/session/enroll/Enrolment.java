package nextstep.courses.domain.session.enroll;

import nextstep.courses.domain.session.student.SelectionStatus;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.domain.session.student.SessionStudents;
import nextstep.courses.dto.EnrolmentInfo;

import static nextstep.courses.domain.session.enroll.RecruitingStatus.*;
import static nextstep.courses.domain.session.student.SelectionStatus.*;

public class Enrolment {

    private Long id;
    private SessionStudents sessionStudents;
    private RecruitingStatus recruitingStatus;

    public Enrolment(SessionStudents sessionStudents, RecruitingStatus recruitingStatus) {
        this.sessionStudents = sessionStudents;
        this.recruitingStatus = recruitingStatus;
    }

    public Enrolment(Long id, SessionStudents sessionStudents, RecruitingStatus recruitingStatus) {
        this(sessionStudents, recruitingStatus);
        this.id = id;
    }

    public SessionStudent enroll(EnrolmentInfo enrolmentInfo) {
        validateRecruitingStatus();

        SessionStudent student = new SessionStudent(enrolmentInfo.getSessionId(), enrolmentInfo.getNsUserId(), WAITING);
        sessionStudents.add(student);

        return student;
    }

    private void validateRecruitingStatus() {
        if (isNotRecruiting(recruitingStatus)) {
            throw new IllegalArgumentException("해당 강의는 현재 모집중이 아닙니다.");
        }
    }

    public SessionStudent selection(SessionStudent student, SelectionStatus selectionStatus) {
        return sessionStudents.selectStudents(student, selectionStatus);
    }

    public int studentsSize() {
        return sessionStudents.size();
    }
}
