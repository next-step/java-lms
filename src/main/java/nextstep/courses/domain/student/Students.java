package nextstep.courses.domain.student;

import java.util.List;

public class Students {

    private final List<Student> students;

    public Students(List<Student> students) {
        this.students = students;
    }

    public int getSize() {
        return students.size();
    }

    public void participate(Student student) {
        students.add(student);
    }
}
