package nextstep.sessions.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SessionStudents {

    private Set<SessionStudent> students = new HashSet<>();

    public SessionStudents() {
        this.students = new HashSet<>();
    }

    public SessionStudents(List<SessionStudent> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            checkStudent(students.get(i), students.get(i+1));
        }
        students.stream().forEach(student -> addStudent(student));
    }

    public int size() {
        return this.students.size();
    }

    public void addStudent(SessionStudent student) {
        for (SessionStudent sessionStudent : students) {
            checkStudent(sessionStudent, student);
        }
        students.add(student);
    }

    private void checkStudent(SessionStudent student1, SessionStudent student2) {
        if (student1.equals(student2)) {
            throw new IllegalArgumentException("이미 수강신청한 학생입니다.");
        }
    }
}
