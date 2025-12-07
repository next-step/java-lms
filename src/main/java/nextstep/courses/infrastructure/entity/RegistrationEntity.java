package nextstep.courses.infrastructure.entity;

import java.time.LocalDateTime;

public class RegistrationEntity {
    private final Long id;
    private final Long sessionId;
    private final Long studentId;
    private final LocalDateTime enrolledAt;

    public RegistrationEntity(Long id, Long sessionId, Long studentId, LocalDateTime enrolledAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.studentId = studentId;
        this.enrolledAt = enrolledAt;
    }

    public Long getId() {
        return id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }
}
