package nextstep.users.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Students {
    private List<Student> students;

    public Students() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public int size() {
        return students.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students students1 = (Students) o;
        return Objects.equals(students, students1.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(students);
    }
}
