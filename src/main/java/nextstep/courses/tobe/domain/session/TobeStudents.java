package nextstep.courses.tobe.domain.session;

import nextstep.courses.tobe.domain.TobeStudent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TobeStudents {
    private final List<TobeStudent> students;

    public TobeStudents(List<TobeStudent> students) {
        this.students = students;
    }

    public TobeStudents(TobeStudent... students) {
        this(new ArrayList<>(List.of(students)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TobeStudents that = (TobeStudents) o;
        return Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(students);
    }

    public void add(TobeStudent student) {
        students.add(student);
    }

    public int size() {
        return students.size();
    }

    public List<TobeStudent> getStudents() {
        return Collections.unmodifiableList(students);
    }
}
