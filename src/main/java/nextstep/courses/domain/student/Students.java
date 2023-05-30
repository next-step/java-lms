package nextstep.courses.domain.student;

import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Students {

    private final List<Student> values;

    public Students() {
        this(new ArrayList<>());
    }

    public Students(List<Student> values) {
        this.values = values;
    }

    public void add(NsUser student, Session session) {
        values.add(new Student(student.getUserId(), session));

    }

    public List<Student> getValues() {
        return values;
    }

    public int size() {
        return values.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students students = (Students) o;
        return Objects.equals(values, students.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }

    @Override
    public String toString() {
        return "Students{" +
                "values=" + values +
                '}';
    }

}
