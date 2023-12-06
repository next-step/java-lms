package nextstep.courses.domain.session;

import nextstep.courses.domain.course.Course;

import java.time.LocalDateTime;

public class Period {

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Period() {}

    public Period(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public boolean isValidDate(Course course) {
        return startDateTime.isBefore(endDateTime) && startDateTime.isAfter(course.getCreatedAt());
    }
}
