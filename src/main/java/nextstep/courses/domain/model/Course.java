package nextstep.courses.domain.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Course extends BaseEntity {
    private final String title;
    private final boolean hasSelection;
    private final Long creatorId;

    public Course(String title, boolean hasSelection, Long creatorId) {
        this(null, title, hasSelection, creatorId, LocalDateTime.now(), LocalDateTime.now());
    }

    public Course(Long id, String title, boolean hasSelection, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.hasSelection = hasSelection;
        this.creatorId = creatorId;
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
