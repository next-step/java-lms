package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Course extends BaseTime {

    public static final String REGISTER_MESSAGE = "기 과정 등록에 성공하였습니다";
    public static final String BLANK = " ";

    private final Long id;
    private final String title;
    private final Long creatorId;
    private final int term;
    private final List<Session> sessions = new ArrayList<>();

    public Course(String title, int term, Long creatorId) {
        this(0L, title, term, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, int term, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        validateTermNumber(term);
        this.id = id;
        this.title = title;
        this.term = term;
        this.creatorId = creatorId;
    }

    private void validateTermNumber(Integer term) {
        if (term <= 0) {
            throw new IllegalArgumentException("the term is must be greater than zero");
        }
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

    public void addSession(Session session) {
        sessions.add(session);
    }

    public int sessionCount() {
        return sessions.size();
    }

    public int fetchTerm() {
        return term;
    }

    public String registerCourse(NsUser user) {
        sessions.forEach(session -> session.enrollInSession(user));
        return String.format("%s%s%d%s", title, BLANK, term, REGISTER_MESSAGE);
    }

    public List<Session> fetchSessions() {
        return Collections.unmodifiableList(sessions);
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
