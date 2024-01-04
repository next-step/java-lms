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

    @Override
    public String toString() {
        return "Students{" +
                "nsUserSessions=" + students +
                '}';
    }
}
