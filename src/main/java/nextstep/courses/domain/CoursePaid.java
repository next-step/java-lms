package nextstep.courses.domain;

import java.time.LocalDateTime;

public class CoursePaid extends Course {

    public CoursePaid(String title, Long creatorId, String courseCoverImageFilePath, Long maxAttendees) {
        super(title, creatorId, courseCoverImageFilePath, maxAttendees);
    }
    
    public CoursePaid(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt, String courseCoverImageFilePath, Long maxAttendees) {
        super(id, title, creatorId, createdAt, updatedAt, courseCoverImageFilePath, maxAttendees);
    }
    
}
