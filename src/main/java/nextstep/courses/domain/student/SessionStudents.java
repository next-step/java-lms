package nextstep.courses.domain.student;

import java.util.List;

import static nextstep.courses.domain.student.StudentEnrollmentStatus.*;

public class SessionStudents {

    private final List<SessionStudent> students;

    public SessionStudents(List<SessionStudent> students) {
        this.students = students;
    }

    public void approve(List<SessionStudent> students) {
        approveStatus(students);
        cancelStatus();
    }

    private void approveStatus(List<SessionStudent> students) {
        this.students.stream()
                .filter(enrollStudent -> students.stream().anyMatch(enrollStudent::sameAs))
                .forEach(SessionStudent::toApproveStatus);
    }

    private void cancelStatus() {
        for (SessionStudent student : students) {
            toCancelStatus(student);
        }
    }

    private void toCancelStatus(SessionStudent student) {
        if (student.getEnrollmentStatus().equals(PENDING)) {
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
