package nextstep.courses.domain;

import java.util.List;
import nextstep.users.domain.NsUser;

public class SessionStudents {

    private final List<Student> students;

    public SessionStudents(List<Student> students) {
        this.students = students;
    }

    public void enroll(Student student) {
        students.add(student);
    }

    public boolean isWithinCapacity(SessionType sessionType) {
        return sessionType.isWithinCapacity(students.size());
    }

    public List<Student> getStudents() {
        return students;
    }
}
