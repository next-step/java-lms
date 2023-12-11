package nextstep.courses.domain.session;

import nextstep.courses.exception.SessionException;
import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.Objects;

public class Students {
    private List<NsUser> students;

    private Integer capacity;

    public Students(List<NsUser> students, Integer capacity) {
        this.students = students;
        this.capacity = capacity;
    }

    public void isOverCapacity() {
        if(students.size() >= capacity) {
            throw new SessionException("최대 수강 인원을 초과하였습니다.");
        }
    }

    public void addStudent(NsUser student) {
        students.add(student);
    }

    public int enrollCount() {
        return students.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students students1 = (Students) o;
        return Objects.equals(students, students1.students) && Objects.equals(capacity, students1.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(students, capacity);
    }
}
