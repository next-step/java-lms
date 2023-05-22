package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Session {
    private Long id;
    private final List<SessionJoin> sessionJoins = new ArrayList<>();

    private final SessionBillType sessionBillType;

    private final SessionCoverImage sessionCoverImage;

    private final SessionRegistration sessionRegistration;

    private final SessionPeriod sessionPeriod;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public Session(Long id, SessionBillType sessionBillType, SessionCoverImage sessionCoverImage, SessionRecruitStatus sessionRecruitStatus, int maxUserCount, SessionPeriod sessionPeriod, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, sessionBillType, sessionCoverImage, SessionRegistration.ready(sessionRecruitStatus, maxUserCount),
             sessionPeriod, createdAt, updatedAt);
    }

    public Session(Long id, SessionBillType sessionBillType, SessionCoverImage sessionCoverImage, SessionRegistration sessionRegistration, SessionPeriod sessionPeriod, LocalDateTime createdAt, LocalDateTime updatedAt) {
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

    public void register(NsUser user) {
        sessionRegistration.validate(sessionJoins.size());
        sessionJoins.add(SessionJoin.apply(this, user));
    }

    public Long getId() {
        return id;
    }

    public List<SessionJoin> getSessionJoins() {
        return sessionJoins;
    }

    public SessionCoverImage getCoverImageUrl() {
        return sessionCoverImage;
    }

    public SessionStatus getSessionStatus() {
        return sessionRegistration.getSessionStatus();
    }

    public SessionRecruitStatus getSessionRecruitStatus() {
        return sessionRegistration.getSessionRecruitStatus();
    }

    public int getMaxUserCount() {
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

    public void addUser(NsUser nsUser) {
        LocalDateTime now = LocalDateTime.now();
        sessionJoins.add(SessionJoin.apply(this, nsUser));
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
