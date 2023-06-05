package nextstep.courses.domain.registration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SessionRegistration {
    private final SessionStatus sessionStatus;
    private final Students students;

    public SessionRegistration(SessionStatus sessionStatus, int maxUserCount) {
        this(sessionStatus, maxUserCount, new HashSet<>());
    }

    public SessionRegistration(SessionStatus sessionStatus, int maxUserCount, Set<Student> students) {
        this(sessionStatus, new Students(maxUserCount, students));
    }

    public SessionRegistration(SessionStatus sessionStatus, Students students) {
        this.sessionStatus = sessionStatus;
        this.students = students;
    }

    public void enroll(Student student, Set<Student> students) {
        validateSessionStatus(sessionStatus);
        this.students.enroll(student);
    }

    private void validateSessionStatus(SessionStatus sessionStatus) {
        if (sessionStatus.isNotRecruiting()) {
            throw new IllegalArgumentException("해당 강의는 모집중이 아닙니다.");
        }
    }

    public Set<Student> getStudents() {
        return students.getUsers();
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public int getMaxUserCount() {
        return students.getMaxUserCount();
    }
}
