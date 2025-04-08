package nextstep.courses.domain;

import java.time.LocalDateTime;

public class CourseFree extends Course {

    public static final Long MAX_ATTENDEES = Long.MAX_VALUE;

    public CourseFree(String title, Long creatorId, String courseCoverImageFilePath) {
        super(title, creatorId, courseCoverImageFilePath, MAX_ATTENDEES);
    }

    public CourseFree(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt, String courseCoverImageFilePath, Long maxAttendees) {
        super(id, title, creatorId, createdAt, updatedAt, courseCoverImageFilePath, maxAttendees);
    }


}
