package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

public class Session {
    private Long id;
    private final SessionRegistration sessionRegistration;
    private final SessionPeriod sessionPeriod;
    private final String sessionCoverImage;
    private final SessionCostType sessionCostType;

    public Session(Long id, SessionRegistration sessionRegistration, SessionPeriod sessionPeriod, String sessionCoverImage, SessionCostType sessionCostType) {
        this.id = id;
        this.sessionRegistration = sessionRegistration;
        this.sessionPeriod = sessionPeriod;
        this.sessionCoverImage = sessionCoverImage;
        this.sessionCostType = sessionCostType;
    }

    public LocalDateTime startedAt() {
        return this.sessionPeriod.getStartedAt();
    }

    public LocalDateTime endedAt() {
        return this.sessionPeriod.getEndedAt();
    }

    public List<NsUser> getUsers() {
        return sessionRegistration.getUsers();
    }

    public String getSessionCoverImage() {
        return sessionCoverImage;
    }

    public SessionCostType getSessionCostType() {
        return this.sessionCostType;
    }

    public SessionStatus getSessionStatus() {
        return sessionRegistration.getSessionStatus();
    }

    public int getMaxUserCount() {
        return sessionRegistration.getMaxUserCount();
    }

    public void register(NsUser user) {
        sessionRegistration.addUser(user);
    }
}
