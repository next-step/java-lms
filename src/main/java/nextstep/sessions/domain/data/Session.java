package nextstep.sessions.domain.data;

import java.time.LocalDateTime;

import nextstep.courses.domain.Course;
import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.type.SessionState;
import nextstep.sessions.domain.data.vo.*;
import nextstep.sessions.domain.exception.SessionsException;
import nextstep.users.domain.NsUser;

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

    public Registration registration(NsUser user, Payment payment) {
        validate(payment);
        return new Registration(this, user, payment);
    }

    private void validate(Payment payment) {
        if (!sessionState.isRecruiting()) {
            throw new SessionsException("모집중이 아닌 강의입니다.");
        }
        sessionType.validatePaidSession(registrations.size(), payment);
    }

}
