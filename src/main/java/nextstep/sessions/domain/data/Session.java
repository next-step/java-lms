package nextstep.sessions.domain.data;

import java.time.LocalDateTime;

import nextstep.courses.domain.Course;
import nextstep.registrations.domain.data.Registrations;
import nextstep.sessions.domain.data.type.SessionState;
import nextstep.sessions.domain.data.vo.*;
import nextstep.sessions.domain.exception.SessionsException;

public class Session {

    private Long id;
    private String name;
    private final SessionType sessionType;
    private final SessionState sessionState;
    private CoverImage coverImage;
    private Duration duration;
    private Course course;
    private Registrations registrations;

    public Session(SessionType sessionType, SessionState sessionState) {
        this.sessionType = sessionType;
        this.sessionState = sessionState;
    }

    public Session(SessionType sessionType, SessionState sessionState, Registrations registrations) {
        this.sessionType = sessionType;
        this.sessionState = sessionState;
        this.registrations = registrations;
    }

    public Session(String name, SessionType sessionType, CoverImage coverImage, LocalDateTime startDate, LocalDateTime endDate, Course course) {
        this.name = name;
        this.sessionType = sessionType;
        this.sessionState = SessionState.PREPARING;
        this.coverImage = coverImage;
        this.duration = new Duration(startDate, endDate);
        this.course = course;
    }

    public Session with(Registrations registrations) {
        return new Session(sessionType, sessionState, registrations);
    }

    public void validateEnrollment() {
        if (!sessionState.isRecruiting()) {
            throw new SessionsException("모집중이 아닌 강의입니다.");
        }
        if (sessionType.isPaid() && !sessionType.isEnoughCapacity(registrations.size())) {
            throw new SessionsException("강의 최대 인원을 초과했습니다.");
        }
    }

}
