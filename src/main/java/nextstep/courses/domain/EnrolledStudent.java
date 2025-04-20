package nextstep.courses.domain;

import java.time.LocalDateTime;

public class EnrolledStudent {
    private Long id;

    private String userId;

    private Session session;

    private LocalDateTime enrolledAt;

    public EnrolledStudent(String userId, Session session, LocalDateTime enrolledAt) {
        this.userId = userId;
        this.session = session;
        this.enrolledAt = enrolledAt;
    }

    public EnrolledStudent(Long id, String userId, Session session, LocalDateTime enrolledAt) {
        this.id = id;
        this.userId = userId;
        this.session = session;
        this.enrolledAt = enrolledAt;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public Session getSession() {
        return session;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }
}
