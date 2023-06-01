package nextstep.courses.domain.registration;

import nextstep.courses.DuplicateStudentRegisterException;
import nextstep.courses.SessionStateNotRecruitStartException;
import nextstep.courses.StudentMaxException;
import nextstep.courses.domain.session.SessionState;
import nextstep.users.domain.Student;
import nextstep.users.domain.Students;

import java.util.Objects;

public class SessionRegistration {
    private SessionState sessionState;
    private RegistrationOpenType registrationOpenType;
    private int maxUser;
    private Students students;

    public SessionRegistration(SessionState sessionState, RegistrationOpenType registrationOpenType, int maxUser) {
        this(sessionState, registrationOpenType, maxUser, new Students());
    }

    public SessionRegistration(SessionState sessionState, RegistrationOpenType registrationOpenType, int maxUser, Students students) {
        this.sessionState = sessionState;
        this.registrationOpenType = registrationOpenType;
        this.maxUser = maxUser;
        this.students = students;
    }

    public Students register(Student student) {
        validateState();
        validateMaxUser();
        validateDuplicateStudent(student);

        return students.addStudent(student);
    }

    private void validateState() {
        if (!registrationOpenType.isOpen()) {
            throw new SessionStateNotRecruitStartException(registrationOpenType.getDescription() + "인 강의입니다.");
        }
    }

    private void validateMaxUser() {
        if (students.size() >= maxUser) {
            throw new StudentMaxException("정원 초과하여 신청할 수 없습니다.");
        }
    }

    private void validateDuplicateStudent(Student student) {
        if (students.isDuplicate(student)) {
            throw new DuplicateStudentRegisterException("중복 강의 수강은 불가합니다.");
        }
    }

    public SessionState getSessionState() {
        return sessionState;
    }

    public int getMaxUser() {
        return maxUser;
    }

    public RegistrationOpenType getRegistrationOpenType() {
        return registrationOpenType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionRegistration that = (SessionRegistration) o;
        return maxUser == that.maxUser && sessionState == that.sessionState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionState, maxUser, students);
    }
}
