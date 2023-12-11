package nextstep.courses.domain;

import nextstep.courses.exception.SessionException;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public class Enrollment {

    private SessionState sessionState;

    private Students students;

    public Enrollment(SessionState sessionState, Students students) {
        this.sessionState = sessionState;
        this.students = students;
    }

    public void enroll(NsUser student) {
        if(!SessionState.isAbleToEnroll(sessionState)) {
            throw new SessionException("모집중인 강의가 아닙니다.");
        }
        students.addStudent(student);
    }

    public void checkSessionState(SessionPeriod sessionPeriod) {
        sessionPeriod.checkSessionStatus(sessionState);
    }

    public void isOverCapacity() {
        students.isOverCapacity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return sessionState == that.sessionState && Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionState, students);
    }
}
