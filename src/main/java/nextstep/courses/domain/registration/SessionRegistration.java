package nextstep.courses.domain.registration;

import nextstep.courses.DuplicateStudentRegisterException;
import nextstep.courses.SessionStateNotRecruitStartException;
import nextstep.courses.StudentMaxException;
import nextstep.courses.domain.session.State;
import nextstep.users.domain.Student;
import nextstep.users.domain.Students;

import java.util.Objects;

public class SessionRegistration {
    private State state;
    private int maxUser;
    private Students students;

    public SessionRegistration(State state, int maxUser) {
        this(state, maxUser, new Students());
    }

    public SessionRegistration(State state, int maxUser, Students students) {
        this.state = state;
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
        if (state != State.RECRUIT_START) {
            throw new SessionStateNotRecruitStartException(state.getDescription() + "인 강의입니다.");
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

    public State getState() {
        return state;
    }

    public int getMaxUser() {
        return maxUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionRegistration that = (SessionRegistration) o;
        return maxUser == that.maxUser && state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, maxUser, students);
    }
}
