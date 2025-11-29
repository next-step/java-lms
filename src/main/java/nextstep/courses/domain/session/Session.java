package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.session.constant.SessionStatus;
import nextstep.courses.domain.session.constant.SessionType;

import java.time.LocalDateTime;

public class Session {

    private final SessionRange sessionRange;
    private final SessionType sessionType;
    private final int maxCapacity;
    private final long tuition;
    private SessionStatus sessionStatus;
    private CoverImage coverImage;

    public Session(LocalDateTime startDate, LocalDateTime endDate, String sessionType, String sessionStatus, CoverImage coverImage) {
        this(startDate, endDate, sessionType, Integer.MAX_VALUE, 0L, sessionStatus, coverImage);
    }

    public Session(LocalDateTime startDate, LocalDateTime endDate, String sessionType, int maxCapacity, long tuition, String sessionStatus, CoverImage coverImage) {
        this(startDate, endDate, SessionType.from(sessionType.toUpperCase()), maxCapacity, tuition, SessionStatus.from(sessionStatus.toUpperCase()), coverImage);
    }

    public Session(LocalDateTime startDate, LocalDateTime endDate, SessionType sessionType, int maxCapacity, long tuition, SessionStatus sessionStatus, CoverImage coverImage) {
       this(new SessionRange(startDate, endDate), sessionType, maxCapacity, tuition, sessionStatus, coverImage);
    }

    public Session(SessionRange sessionRange, SessionType sessionType, int maxCapacity, long tuition, SessionStatus sessionStatus, CoverImage coverImage) {
        this.sessionRange = sessionRange;
        this.sessionType = sessionType;
        this.maxCapacity = maxCapacity;
        this.tuition = tuition;
        this.sessionStatus = sessionStatus;
        this.coverImage = coverImage;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public long getTuition() {
        return tuition;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }
}
