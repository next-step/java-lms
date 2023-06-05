package nextstep.courses.domain.registration;

public class StudentBuilder {
    private Long nsUserId;
    private Long sessionId;

    private StudentBuilder() {
    }

    public static StudentBuilder aStudentBuilder() {
        return new StudentBuilder();
    }

    public StudentBuilder withUserId(Long nsUserId) {
        this.nsUserId = nsUserId;
        return this;
    }

    public StudentBuilder withSessionId(Long sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public Student build() {
        Student student = new Student(nsUserId, sessionId);
        return student;
    }
}
