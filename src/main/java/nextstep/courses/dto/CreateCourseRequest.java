package nextstep.courses.dto;

import nextstep.courses.domain.Course;

public class CreateCourseRequest {

    private String title;
    private Long creatorId;

    public Course toEntity() {
        return new Course(title, creatorId);
    }
}
