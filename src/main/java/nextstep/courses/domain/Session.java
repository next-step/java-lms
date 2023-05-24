package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Session {
    private Long id;

    private final SessionStatus sessionStatus;

    private final SessionBillType sessionBillType;

    private final SessionCoverImage sessionCoverImage;

    private final SessionRegistration sessionRegistration;

    private final SessionPeriod sessionPeriod;

    private final SessionJoins sessionJoins;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public Session(Long id, SessionStatus sessionStatus, SessionBillType sessionBillType, SessionCoverImage sessionCoverImage,
                   SessionRecruitStatus sessionRecruitStatus, long maxUserCount, SessionPeriod sessionPeriod,
                   SessionJoins sessionJoins, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, sessionStatus, sessionBillType, sessionCoverImage, SessionRegistration.of(sessionRecruitStatus, maxUserCount),
             sessionPeriod, sessionJoins, createdAt, updatedAt);
    }

    public Session(Long id, SessionStatus sessionStatus, SessionBillType sessionBillType, SessionCoverImage sessionCoverImage,
                   SessionRegistration sessionRegistration, SessionPeriod sessionPeriod, SessionJoins sessionJoins,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.sessionStatus = sessionStatus;
        this.sessionJoins = sessionJoins;
        if (sessionBillType == null) {
            throw new IllegalArgumentException("과금 유형을 선택해주세요");
        }

        if (sessionPeriod == null) {
            throw new IllegalArgumentException("강의 기간을 설정해주세요");
        }

        this.id = id;
        this.sessionBillType = sessionBillType;
        this.sessionCoverImage = sessionCoverImage;
        this.sessionRegistration = sessionRegistration;
        this.sessionPeriod = sessionPeriod;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void register(NsUser user, SessionJoinStatus sessionJoinStatus) {
        if (!this.sessionStatus.isRegister()) {
            throw new IllegalArgumentException("강의가 모집중/진행중이 아닙니다. 현재상태:" + sessionStatus);
        }

        sessionRegistration.validate(sessionJoins.isApproveStatusCount());
        this.addUser(user, sessionJoinStatus);
    }

    public Long getId() {
        return id;
    }

    public List<SessionJoin> getSessionJoins() {
        return sessionJoins.value();
    }

    public SessionCoverImage getCoverImageUrl() {
        return sessionCoverImage;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public SessionRecruitStatus getSessionRecruitStatus() {
        return sessionRegistration.getSessionRecruitStatus();
    }

    public long getMaxUserCount() {
        return sessionRegistration.getMaxUserCount();
    }

    public SessionPeriod getSessionPeriod() {
        return sessionPeriod;
    }

    public SessionBillType getSessionBillType() {
        return sessionBillType;
    }

    public String getSessionCoverImageUrl() {
        return sessionCoverImage.getUrl();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    private void addUser(NsUser nsUser, SessionJoinStatus sessionJoinStatus) {
        sessionJoins.add(SessionJoin.apply(this, nsUser, sessionJoinStatus));
    }

    public void addSessionJoins(List<SessionJoin> sessionJoins) {
        this.sessionJoins.addAll(sessionJoins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Session{" + "id=" + id + ", sessionJoins=" + sessionJoins + ", sessionBillType=" + sessionBillType + ", sessionCoverImage=" + sessionCoverImage + ", sessionRegistration=" + sessionRegistration + ", sessionPeriod=" + sessionPeriod + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
}
