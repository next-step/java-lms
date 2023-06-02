package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Session {
    private Long id;
    private final List<SessionJoin> sessionJoins = new ArrayList<>();

    private final SessionBilling sessionBilling;
    private final SessionStatus sessionStatus;
    private final String sessionCoverImage;
    private final int maxUserCount;

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

        this.maxUserCount = maxUserCount;
        this.id = id;
        this.sessionBilling = sessionBillingType;
        this.sessionStatus = sessionStatus == null ? SessionStatus.OPEN : sessionStatus;
        this.sessionCoverImage = sessionCoverImage;
        this.sessionPeriod = sessionPeriod;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void register(NsUser user) {
        if (!this.sessionStatus.isOpen()) {
            throw new IllegalArgumentException("수강신청은 모집중일때만 등록이 가능합니다.");
        }

        if (maxUserCount <= sessionJoins.size()) {
            throw new IllegalArgumentException("최대 수강인원을 초과하였습니다.");
        }

        sessionJoins.add(new SessionJoin(this, user, LocalDateTime.now(), null));
    }

    public Long getId() {
        return id;
    }

    public List<SessionJoin> getSessionJoins() {
        return sessionJoins;
    }

    public SessionBilling getSessionBilling() {
        return sessionBilling;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public String getSessionCoverImage() {
        return sessionCoverImage;
    }

    public int getMaxUserCount() {
        return maxUserCount;
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
        LocalDateTime now = LocalDateTime.now();
        sessionJoins.add(new SessionJoin(this, nsUser, now, now));
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
                ", sessionBillingType=" + sessionBilling +
                ", sessionStatus=" + sessionStatus +
                ", sessionCoverImage='" + sessionCoverImage  +
                ", maxUserCount=" + maxUserCount +
                ", sessionPeriod=" + sessionPeriod +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
