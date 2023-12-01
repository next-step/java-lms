package nextstep.sessions.domain.data;

import java.time.LocalDateTime;

import nextstep.courses.domain.Course;
import nextstep.sessions.domain.data.type.SessionState;
import nextstep.sessions.domain.data.type.SessionType;
import nextstep.sessions.domain.data.vo.CoverImage;
import nextstep.sessions.domain.data.vo.Duration;

public class Session {

    private Long id;
    private final Course course;
    private final String name;
    private final SessionType sessionType;
    private final int fee;
    private final int capacity;

    private final SessionState sessionState;
    private final CoverImage coverImage;
    private final Duration duration;

    public Session(Course course, String name, SessionType sessionType, int fee, int capacity, CoverImage coverImage, LocalDateTime startDate, LocalDateTime endDate) {
        this.course = course;
        this.name = name;
        this.sessionType = sessionType;
        this.fee = fee;
        this.capacity = capacity;
        this.sessionState = SessionState.PREPARING;
        this.coverImage = coverImage;
        this.duration = new Duration(startDate, endDate);
    }

    public boolean isPaid() {
        return sessionType.isPay();
    }

    public int capacity() {
        return capacity;
    }

}
