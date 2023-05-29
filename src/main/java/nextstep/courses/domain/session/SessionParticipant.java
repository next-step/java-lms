package nextstep.courses.domain.session;

import nextstep.courses.domain.student.Student;
import nextstep.courses.domain.student.Students;

public class SessionParticipant {

    private final int maximumStudent;

    private Students students;

    public SessionParticipant(int maximumStudent) {
        this.maximumStudent = maximumStudent;
    }

    public SessionParticipant(int maximumStudent, Students students) {
        this.maximumStudent = maximumStudent;
        this.students = students;
    }

    public boolean isFullSession() {
        return students.getSize() > maximumStudent;
    }

    public void participateStudent(Student student) {
        students.participate(student);
    }

    public int getStudentsCount() {
        return students.getSize();
    }
}
