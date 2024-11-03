package nextstep.courses.domain.session;

import nextstep.courses.domain.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class Students {

    private final List<Student> students;

    public Students(List<Student> students) {
        this.students = new ArrayList<>(students);
    }

    public Students(Student... students) {
        this(List.of(students));
    }

    public void add(Student student) {
        students.add(student);
    }

    public int size() {
        return students.size();
    }

    public void each(Consumer<Student> consumer) {
        students.forEach(consumer);
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students that = (Students) o;
        return Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(students);
    }
}