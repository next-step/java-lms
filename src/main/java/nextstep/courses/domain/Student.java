package nextstep.courses.domain;

public class Student {

    private final Long studentId;
    private final Long sessionId;

    public Student(Long studentId, Long sessionId) {
        this.studentId = studentId;
        this.sessionId = sessionId;
    }

    public void enroll(Session session) {
        session.enroll(this);
    }
}
