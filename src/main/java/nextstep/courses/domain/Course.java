package nextstep.courses.domain;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class Course {
    private Long id;

    private String title;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private SessionList sessions;

    private String courseCoverImageFilePath;

    private CourseCoverImage courseCoverImage;

    private Long maxAttendees;

    public Course() {
    }

    public Course(String title, Long creatorId, String courseCoverImageFilePath) {
        this(0L, title, creatorId, LocalDateTime.now(), null, courseCoverImageFilePath, null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt, String courseCoverImageFilePath, Long maxAttendees) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.courseCoverImageFilePath = courseCoverImageFilePath;
        this.courseCoverImage = new CourseCoverImage(courseCoverImageFilePath);
        this.maxAttendees = maxAttendees;
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
