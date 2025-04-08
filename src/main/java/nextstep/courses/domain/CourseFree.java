package nextstep.courses.domain;

import java.time.LocalDateTime;

public class CourseFree extends Course {

    public static final Long MAX_ATTENDEES = Long.MAX_VALUE;

    public CourseFree(String title, Long creatorId, String courseCoverImageFilePath) {
        super(title, creatorId, courseCoverImageFilePath, MAX_ATTENDEES);
    }

    public CourseFree(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt, String courseCoverImageFilePath) {
        super(id, title, creatorId, createdAt, updatedAt, courseCoverImageFilePath, MAX_ATTENDEES, CourseStatus.PREPARING);
    }

    public CourseFree(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt, String courseCoverImageFilePath, CourseStatus courseStatus) {
        super(id, title, creatorId, createdAt, updatedAt, courseCoverImageFilePath, MAX_ATTENDEES, courseStatus);
    }
}
