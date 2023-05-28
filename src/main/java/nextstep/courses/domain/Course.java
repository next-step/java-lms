package nextstep.courses.domain;

import nextstep.courses.domain.base.BaseDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Course extends BaseDate {

    private Long id;

    private String title;

    private Sessions sessions = new Sessions();

    private Long creatorId;

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

    public Session openSession(Long id,
                               LocalDate sessionStartDate,
                               LocalDate sessionEndDate,
                               String imageUrl,
                               boolean paid,
                               int recruitmentCount) {
        return sessions.openSession(id, this, sessionStartDate, sessionEndDate, imageUrl, paid, recruitmentCount);
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
