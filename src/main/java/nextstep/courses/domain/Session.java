package nextstep.courses.domain;

import nextstep.courses.SessionStateNotOnException;
import nextstep.courses.StudentMaxException;
import nextstep.users.domain.Student;
import nextstep.users.domain.Students;

import java.util.Objects;

public class Session {
    private Long id;
    private String title;
    private String cover;
    private int cardinalNumber;
    private Cost cost;
    private State state;
    private int maxUser;
    private Students students;

    Session(String title, String cover, int cardinalNumber, Cost cost, State state, int maxUser) {
        this(0L, title, cover, cardinalNumber, cost, state, maxUser);
    }

    Session(Long id, String title, String cover, int cardinalNumber, Cost cost, State state, int maxUser) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.cardinalNumber = cardinalNumber;
        this.cost = cost;
        this.state = state;
        this.maxUser = maxUser;
        this.students = new Students();
    }

    public static Session of(String title, String cover, int cardinalNumber, Cost cost, State state, int maxUser) {
        return new Session(title, cover, cardinalNumber, cost, state, maxUser);
    }

    public Students addStudent(Student student) {
        validateState();
        validateStudentsNumber();
        students.addStudent(student);
        return students;
    }

    public void validateState() {
        if (state == State.READY) {
            throw new SessionStateNotOnException("준비 중인 강의입니다.");
        }
        if (state == State.OFF) {
            throw new SessionStateNotOnException("모집 종료된 강의입니다.");
        }
    }

    public void validateStudentsNumber() {
        if (students.size() == maxUser) {
            throw new StudentMaxException("정원 초과하여 신청할 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return cardinalNumber == session.cardinalNumber && maxUser == session.maxUser && Objects.equals(id, session.id) && Objects.equals(title, session.title) && Objects.equals(cover, session.cover) && cost == session.cost && state == session.state && Objects.equals(students, session.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, cover, cardinalNumber, cost, state, maxUser, students);
    }
}
