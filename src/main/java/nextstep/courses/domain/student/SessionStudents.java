package nextstep.courses.domain.student;

import java.util.List;

public class SessionStudents {

    private final List<SessionStudent> students;

    public SessionStudents(List<SessionStudent> students) {
        this.students = students;
    }

    public void toApproveStatus() {
        for (SessionStudent student : students) {
            student.toApproveStatus();
        }
    }

    public void toCancelStatus() {
        for (SessionStudent student : students) {
            student.toCancelStatus();
        }
    }

    public void add(SessionStudent student) {
        students.add(student);
    }

    public int size() {
        return students.size();
    }

    public List<SessionStudent> get() {
        return students;
    }
}
