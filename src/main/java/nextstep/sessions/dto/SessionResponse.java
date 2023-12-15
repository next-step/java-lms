package nextstep.sessions.dto;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionStatus;
import nextstep.sessions.domain.SessionType;

import java.time.LocalDate;

public class SessionResponse {

    private Long id;

    private String name;

    private SessionStatus sessionStatus;

    private SessionType sessionType;

    private LocalDate startDt;

    private LocalDate endDt;

    public SessionResponse(Long id, String name, SessionStatus sessionStatus, SessionType sessionType,
                           LocalDate startDt, LocalDate endDt) {
        this.id = id;
        this.name = name;
        this.sessionStatus = sessionStatus;
        this.sessionType = sessionType;
        this.startDt = startDt;
        this.endDt = endDt;
    }

    public static SessionResponse of(Session session) {
        return new SessionResponse(session.id(), session.name(), session.sessionStatus(), session.sessionType(),
                session.startDt(), session.endDt());
    }

    public Long id() {
        return id;
    }
}
