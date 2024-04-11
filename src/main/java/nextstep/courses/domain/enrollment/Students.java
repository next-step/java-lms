package nextstep.courses.domain.enrollment;

import java.util.ArrayList;
import java.util.List;

public class Students {

    private final Long sessionId;
    private final List<Student> students;

    public Students(Long sessionId) {
        this(sessionId, new ArrayList<>());
    }

    public Students(Long sessionId, List<Student> students) {
        this.sessionId = sessionId;
        this.students = students;
    }

    public void add(Student student) {
        students.add(student);
    }

    public int size() {
        return students.size();
    }
}
