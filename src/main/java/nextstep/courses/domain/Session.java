package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Session {
    private Long id;
   // private final List<SessionJoin> sessionJoins = new ArrayList<>();

    private final SessionBilling sessionBilling;
    private final String sessionCoverImage;
    private final SessionRegistration sessionRegistration;
    private final SessionPeriod sessionPeriod;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Session(Long id, SessionBilling sessionBillingType, SessionStatus sessionStatus, String sessionCoverImage, int maxUserCount, SessionPeriod sessionPeriod, LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (sessionBillingType == null) {
            throw new IllegalArgumentException("과금 유형을 선택해주세요");
        }

        if (sessionPeriod == null) {
            throw new IllegalArgumentException("강의 기간을 설정해주세요");
        }

        this.id = id;
        this.sessionBilling = sessionBillingType;
        this.sessionCoverImage = sessionCoverImage;
        this.sessionPeriod = sessionPeriod;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.sessionRegistration = new SessionRegistration(this, sessionStatus, maxUserCount);
    }

    public void register(NsUser user) {
        sessionRegistration.register(user);
    }

    public Long getId() {
        return id;
    }

    public List<SessionJoin> getSessionJoins() {
        return sessionRegistration.getSessionJoins();
    }

    public SessionBilling getSessionBilling() {
        return sessionBilling;
    }

    public SessionStatus getSessionStatus() {
        return sessionRegistration.getSessionStatus();
    }

    public String getSessionCoverImage() {
        return sessionCoverImage;
    }

    public int getMaxUserCount() {
        return sessionRegistration.getMaxUserCount();
    }

    public SessionPeriod getSessionPeriod() {
        return sessionPeriod;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void addUser(NsUser nsUser) {
        sessionRegistration.addUser(nsUser);
    }


        @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", sessionBilling=" + sessionBilling +
                ", sessionCoverImage='" + sessionCoverImage +
                ", sessionRegistration=" + sessionRegistration +
                ", sessionPeriod=" + sessionPeriod +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
