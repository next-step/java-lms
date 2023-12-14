package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.Objects;

public class Enrollment {

    private Students students;

    public Enrollment(Students students) {
        this.students = students;
    }

    public void enroll(NsUser student) {
        students.addStudent(student);
    }

    public void isOverCapacity(int capacity) {
        students.isOverCapacity(capacity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(students);
    }
}
