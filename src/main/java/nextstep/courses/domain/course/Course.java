package nextstep.courses.domain.course;

import nextstep.common.BaseEntity;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.Sessions;

import java.time.LocalDateTime;
import java.util.List;

public class Course extends BaseEntity {
    private String title;
    private Long creatorId;
    private Sessions sessions;

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.creatorId = creatorId;
    }

    public void registerSession(Long sessionId, int studentsCount) {
        this.sessions.registerSession(sessionId, studentsCount);
    }

    public void checkIsRegisterSession(Long sessionId, int studentsCount) {
        this.sessions.checkIsRegister(sessionId, studentsCount);
    }

    public String getTitle() {
        return this.title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return super.getCreatedAt();
    }

    public void receiveSession(List<Session> sessions) {
        this.sessions = new Sessions(sessions);
    }
}
