package nextstep.sessions.domain;

import nextstep.courses.domain.Course;

import java.time.LocalDateTime;

public class Session {
    private Long id;

    private Course course;

    private LocalDateTime from;

    private LocalDateTime to;

    public static Session of(Long id, Course course) {
        return new Session(id, course, LocalDateTime.MIN, LocalDateTime.MAX);
    }

    public static Session of(Long id, Course course, LocalDateTime from, LocalDateTime to) {
        return new Session(id, course, from, to);
    }

    private Session(Long id, Course course, LocalDateTime from, LocalDateTime to) {
        validatePeriod(from, to);
        this.id = id;
        this.course = course;
        this.from = from;
        this.to = to;
    }

    public void toCourse(Course course) {
        this.course = course;
    }

    private static void validatePeriod(LocalDateTime from, LocalDateTime to) {
        if(from == null || to == null) {
            throw new IllegalArgumentException("시작일 또는 종료일은 null 일 수 없습니다.");
        }

        if (from.isEqual(to) || from.isAfter(to)) {
            throw new IllegalArgumentException("시작일이 종료일과 같거나 이후일 수 없습니다.");
        }
    }
}
