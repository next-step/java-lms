package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course {

    private Long id;
    private String title;
    private Long creatorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Map<Integer, List<Session>> sessions = new HashMap<>();

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

    public void addSession(int year, Session session) {
        validateDuplicatedSession(year, session);
        this.sessions.computeIfAbsent(year, k -> new ArrayList<>()).add(session);
    }

    private void validateDuplicatedSession(int year, Session session) {
        if (sessions.containsKey(year) && sessions.get(year).contains(session)) {
            throw new IllegalArgumentException("이미 동일한 강의가 있습니다.");
        }
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

    public boolean register(NsUser user, Session session, Payment payment) {
        return false;
    }
}
