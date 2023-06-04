package nextstep.courses.domain.registration;

import nextstep.courses.DuplicateStudentEnrollException;
import nextstep.courses.SessionStateNotRecruitStartException;
import nextstep.courses.StudentMaxException;
import nextstep.courses.domain.session.SessionState;
import nextstep.users.domain.Student;
import nextstep.users.domain.Students;

import java.util.Objects;

public class SessionEnrollment {
    private SessionState sessionState;
    private EnrollmentOpenType enrollmentOpenType;
    private int maxUser;
    private Students students;

    public SessionEnrollment(SessionState sessionState, EnrollmentOpenType enrollmentOpenType, int maxUser) {
        this(sessionState, enrollmentOpenType, maxUser, new Students());
    }

    public SessionEnrollment(SessionState sessionState, EnrollmentOpenType enrollmentOpenType, int maxUser, Students students) {
        this.sessionState = sessionState;
        this.enrollmentOpenType = enrollmentOpenType;
        this.maxUser = maxUser;
        this.students = students;
    }

    public Students enroll(Student student) {
        validateState();
        validateMaxUser();
        validateDuplicateStudent(student);

        return students.addStudent(student);
    }

    private void validateState() {
        if (!enrollmentOpenType.isOpen()) {
            throw new SessionStateNotRecruitStartException(enrollmentOpenType.getDescription() + "인 강의입니다.");
        }
    }

    private void validateMaxUser() {
        if (students.size() >= maxUser) {
            throw new StudentMaxException("정원 초과하여 신청할 수 없습니다.");
        }
    }

    private void validateDuplicateStudent(Student student) {
        if (students.isDuplicate(student)) {
            throw new DuplicateStudentEnrollException("중복 강의 수강은 불가합니다.");
        }
    }

    public SessionState getSessionState() {
        return sessionState;
    }

    public int getMaxUser() {
        return maxUser;
    }

    public EnrollmentOpenType getRegistrationOpenType() {
        return enrollmentOpenType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionEnrollment that = (SessionEnrollment) o;
        return maxUser == that.maxUser && sessionState == that.sessionState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionState, maxUser, students);
    }
}
