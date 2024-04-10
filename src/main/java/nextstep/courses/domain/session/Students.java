package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.List;

public class Students {

    private final Long sessionId;
    private final List<Student> students;

    public Students(Long sessionId) {
        this.sessionId = sessionId;
        this.students = new ArrayList<>();
    }

    public void add(Student student) {
        students.add(student);
    }

    public int size() {
        return students.size();
    }
}
