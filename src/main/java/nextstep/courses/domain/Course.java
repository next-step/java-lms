package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Course {

    private Long id;
    private String title;
    private Long cohort; // 기수 (1기, 2기, ...)
    private Long creatorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Session> sessions = new ArrayList<>();

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, 0L, creatorId);
    }

    public Course(Long id, String title, Long cohort, Long creatorId) {
        this(id, title, cohort, creatorId, LocalDateTime.now(), LocalDateTime.now());
    }

    public Course(Long id, String title, Long cohort, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.cohort = cohort;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void addSession(Session session) {
        validateDuplicatedSession(session);
        this.sessions.add(session);
    }

    private void validateDuplicatedSession(Session session) {
        if (sessions.contains(session)) {
            throw new IllegalArgumentException("이미 동일한 강의가 있습니다.");
        }
    }

    public boolean isIncludeSession(Session session) {
        return this.sessions.contains(session);
    }

    public void register(NsUser user, Session session, Payment payment) {
        verifyIncludedSession(session);
        session.enroll(user, payment);
    }

    private void verifyIncludedSession(Session session) {
        if (!sessions.contains(session)) {
            throw new IllegalArgumentException("과정에 포함되지 않은 강의입니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getCohort() {
        return cohort;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
