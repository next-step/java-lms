package nextstep.sessions.domain.data;

import java.time.LocalDateTime;

import nextstep.courses.domain.Course;
import nextstep.sessions.domain.data.type.SessionState;
import nextstep.sessions.domain.data.type.SessionType;
import nextstep.sessions.domain.data.vo.CoverImage;
import nextstep.sessions.domain.data.vo.Duration;

public class Session {

    private Long id;
    private Course course;
    private String name;
    private final SessionType sessionType;
    private final int fee;
    private final int capacity;
    private final SessionState sessionState;
    private CoverImage coverImage;
    private Duration duration;

    public Session(SessionType sessionType, int fee, int capacity, SessionState sessionState) {
        this.sessionType = sessionType;
        this.fee = fee;
        this.capacity = capacity;
        this.sessionState = sessionState;
    }

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

    public static Session ofPaidSession(int fee, int capacity, SessionState sessionState) {
        return new Session(SessionType.PAY, fee, capacity, sessionState);
    }

    public boolean isPaid() {
        return sessionType.isPay();
    }

    public boolean isRecruiting() {
        return sessionState.isRecruiting();
    }

    public int capacity() {
        return capacity;
    }

}
