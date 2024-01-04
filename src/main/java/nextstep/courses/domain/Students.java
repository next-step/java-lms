package nextstep.courses.domain;

import java.util.Arrays;
import java.util.List;

public class Students {
    private final List<Student> students;

    public Students(Student... students) {
        this(Arrays.asList(students));
    }

    public Students(List<Student> students) {
        this.students = students;
    }

    public Long approvedUserNumber() {
        return students.stream().filter(Student::isApproved).count();
    }

    @Override
    public String toString() {
        return "Students{" +
                "nsUserSessions=" + students +
                '}';
    }
}
