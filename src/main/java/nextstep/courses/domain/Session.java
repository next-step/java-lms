package nextstep.courses.domain;

import java.util.HashSet;
import java.util.Set;

public class Session {

    private final Set<Student> students = new HashSet<>();

    public Session() {

    }

    public void add(Student student) {
        students.add(student);
    }

    public int totalStudentNum() {
        return students.size();
    }
}
