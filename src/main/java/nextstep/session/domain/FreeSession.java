package nextstep.session.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FreeSession extends Session {
    private static final SessionType SESSION_TYPE = SessionType.FREE;

    public FreeSession(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long creatorId, LocalDate startDate, LocalDate endDate, SessionStatus sessionStatus, SessionType sessionType) {
        this(id, createdAt, updatedAt, creatorId, startDate, endDate, null, sessionStatus, sessionType);
    }

    public FreeSession(Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage) {
        super(creatorId, startDate, endDate, sessionImage, SESSION_TYPE);
    }

    public FreeSession(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, SessionStatus sessionStatus, SessionType sessionType) {
        super(id, createdAt, updatedAt, creatorId, startDate, endDate, sessionImage, sessionStatus, sessionType);
    }

    public static FreeSession create(Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage) {
        return new FreeSession(creatorId, startDate, endDate, sessionImage);
    }
}
