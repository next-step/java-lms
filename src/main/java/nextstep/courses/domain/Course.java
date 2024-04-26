package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.courses.CannotRegisterException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Course {

    private Long id;

    private String title;

    private Long creatorId;

    private Sessions sessions;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int countOfSession() {
        return sessions.size();
    }

    public void add(Session session) {
        session.toCourse(this);
        if (Objects.isNull(sessions)) {
            sessions = Sessions.from(session);
            return;
        }
        sessions.addSession(session);
    }

    public void register(Long sessionId, NsUser user) throws CannotRegisterException {
        this.sessions.register(sessionId, user);
    }

    public void register(Long sessionId, NsUser user, Payment payment) throws CannotRegisterException {
        this.sessions.register(sessionId, user, payment);
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
