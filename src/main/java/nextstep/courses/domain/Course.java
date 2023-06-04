package nextstep.courses.domain;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionFeeType;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.Sessions;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Course extends AbstractCourse {
    private final Sessions sessions = new Sessions();

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

    public Course initSessions(List<Session> sessions) {
        this.sessions.init(sessions);
        return this;
    }

    public Session findSession(Long sessionId) {
        return sessions.findSession(sessionId);
    }

    public Session createSession(
            Long courseId,
            NsUser nsUser,
            String title,
            Long sessionNumber,
            LocalDate startDate,
            LocalDate endDate,
            String url,
            SessionFeeType sessionFeeType,
            SessionStatus sessionStatus,
            int capacity
    ) {
        return sessions.create(courseId, nsUser, title, sessionNumber, startDate, endDate, url, sessionFeeType, sessionStatus, capacity);
    }

    @Override
    public String toString() {
        return "Course{" +
                "sessions=" + sessions +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
