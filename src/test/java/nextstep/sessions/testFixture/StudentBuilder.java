package nextstep.sessions.testFixture;

import java.time.LocalDateTime;
import nextstep.sessions.domain.students.Student;
import nextstep.sessions.domain.students.StudentStatus;

public class StudentBuilder {
    private Long id = 1L;
    private Long sessionId = 1L;
    private Long nsUserId = 1L;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private StudentStatus studentStatus = StudentStatus.WAITING;

    public static StudentBuilder aStudent() {
        return new StudentBuilder();
    }

    public StudentBuilder withId(Long id) {
        this.id = id;

        return this;
    }

    public StudentBuilder withSessionId(Long sessionId) {
        this.sessionId = sessionId;

        return this;
    }

    public StudentBuilder withNsUserId(Long nsUserId) {
        this.nsUserId = nsUserId;

        return this;
    }

    public StudentBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;

        return this;
    }

    public StudentBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;

        return this;
    }

    public StudentBuilder withStudentStatus(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;

        return this;
    }

    public Student build() {
        return new Student(id, sessionId, nsUserId, studentStatus, createdAt, updatedAt);
    }
}
