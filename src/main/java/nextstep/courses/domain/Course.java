package nextstep.courses.domain;

import nextstep.courses.domain.type.CourseStatus;
import nextstep.courses.domain.type.CourseType;

import java.time.LocalDateTime;

public class Course {
    private final Long id;

    private final String title;

    private final Long creatorId;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;
    private final CourseStatus courseStatus;
    private final CourseType courseType;

    public Course(String title,
                  Long creatorId,
                  CourseType courseType) {
        this(0L, title, creatorId, LocalDateTime.now(), LocalDateTime.now(), CourseStatus.READY, courseType);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt,
                  LocalDateTime updatedAt, CourseStatus courseStatus, CourseType courseType) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.courseStatus = courseStatus;
        this.courseType = courseType;
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

    public CourseStatus getCourseStatus() { return courseStatus; }

    public CourseType getCourseType() { return courseType; }

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

    public boolean isNotRecruiting() {
        return this.courseStatus != CourseStatus.RECRUIT;
    }
}
