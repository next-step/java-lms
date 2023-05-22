package nextstep.courses.domain;

import java.util.List;

public class Students {
    private final List<Student> students;

    public Students(List<Student> students) {
        this.students = students;
    }


    public boolean enrolledUser(Student student) {
        return students.contains(student);
    }
}
