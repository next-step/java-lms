package nextstep.courses.domain.session;

import nextstep.courses.domain.Student;

import java.util.*;

public class Students {
    private final List<Student> students;


    public Students(Student... students) {
        this(List.of(students));
    }

    public Students(List<Student> students) {
        this.students = new ArrayList<>(students);
    }

    public void add(Student student) {
        this.students.add(student);
    }

    public int size() {
        return students.size();
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(this.students);
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

    @Override
    public String toString() {
        return "Students{" +
                "students=" + students +
                '}';
    }
}