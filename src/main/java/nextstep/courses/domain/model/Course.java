package nextstep.courses.domain.model;

import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course extends BaseEntity {
    private final boolean hasSelection;
    private final List<Session> sessions;
    private final String title;
    private final Long creatorId;

    public Course(String title, Boolean hasSelection, Long creatorId) {
        this(null, title, hasSelection, creatorId, new ArrayList<>(), LocalDateTime.now(), LocalDateTime.now());
    }

    public Course(Long id, String title, Boolean hasSelection, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, title, hasSelection, creatorId, new ArrayList<>(), createdAt, updatedAt);
    }

    public Course(Long id, String title, Boolean hasSelection, Long creatorId, @NonNull List<Session> sessions, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.hasSelection = hasSelection;
        this.creatorId = creatorId;
        this.sessions = sessions;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public boolean hasSelection() {
        return hasSelection;
    }

    public boolean hasSameTitle(Course savedCourse) {
        return title.equals(savedCourse.title);
    }

    public boolean hasSameSelection(Course savedCourse) {
        return hasSelection == savedCourse.hasSelection;
    }

    public boolean include(Session session) {
        return sessions.contains(session);
    }

    public Map<String, Object> getParameters() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", getId());
        map.put("created_at", getCreatedAt());
        map.put("updated_at", getUpdatedAt());
        map.put("title", title);
        map.put("has_selection", hasSelection);
        map.put("creator_id", creatorId);
        return map;
    }
}
