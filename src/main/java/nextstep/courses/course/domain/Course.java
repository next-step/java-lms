package nextstep.courses.course.domain;

import java.time.LocalDateTime;
import nextstep.courses.course.domain.enumaration.CourseChargeType;

public class Course {
    private Long id;

    private String title;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private CourseChargeType courseChargeType;

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null, CourseChargeType.PAID);
    }

    public Course(String title, Long creatorId, CourseChargeType courseChargeType) {
        this(0L, title, creatorId, LocalDateTime.now(), null, courseChargeType);
    }

    public Course(
            Long id,
            String title,
            Long creatorId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.courseChargeType = CourseChargeType.PAID;
    }

    public Course(
            Long id,
            String title,
            Long creatorId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            CourseChargeType courseChargeType
    ) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.courseChargeType = courseChargeType;
    }

    public boolean isPaid() {
        return this.courseChargeType.equals(CourseChargeType.PAID);
    }

    public boolean isFree() {
        return this.courseChargeType.equals(CourseChargeType.FREE);
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
