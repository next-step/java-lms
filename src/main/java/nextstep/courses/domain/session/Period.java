package nextstep.courses.domain.session;

import nextstep.courses.domain.course.Course;

import java.time.LocalDateTime;

public class Period {

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Period(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (startDateTime.isAfter(endDateTime)) {
            throw new IllegalArgumentException("시작 날짜가 종료 날짜 보다 이후일 수 없습니다.");
        }
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public boolean isAfterStartDateTime(Course course) {
        return startDateTime.isAfter(course.getCreatedAt());
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
}
