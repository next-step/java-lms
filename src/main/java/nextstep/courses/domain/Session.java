package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {

    private long id;
    private String title;
    private SessionType sessionType;
    private SessionState state;
    private String image;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private Course course;

    public Session(long id, String title, SessionType sessionType, SessionState state, String image, LocalDateTime startDate,
            LocalDateTime endDate) {
        this.id = id;
        this.title = title;
        this.sessionType = sessionType;
        this.state = state;
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void toCourse(Course course) {
        this.course = course;
    }
}
