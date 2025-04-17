package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private final List<Student> students;

    public Students() {
        this.students = new ArrayList<>();
    }

    public Students(List<Student> students) {
        this.students = students;
    }

    public int count() {
        return students.size();
    }

    public void add(Student student) {
        students.add(student);
    }
}
