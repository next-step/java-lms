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

    private final SessionStatus sessionStatus;

    private final SessionRecruitStatus sessionRecruitStatus;

    private final int maxUserCount;

    private final SessionPeriod sessionPeriod;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public Session(Long id, SessionBillType sessionBillType, SessionCoverImage sessionCoverImage, SessionStatus sessionStatus, SessionRecruitStatus sessionRecruitStatus, int maxUserCount, SessionPeriod sessionPeriod, LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (sessionBillType == null) {
            throw new IllegalArgumentException("과금 유형을 선택해주세요");
        }

        if (sessionPeriod == null) {
            throw new IllegalArgumentException("강의 기간을 설정해주세요");
        }

        this.id = id;
        this.sessionBillType = sessionBillType;
        this.sessionCoverImage = sessionCoverImage;
        this.sessionStatus = sessionStatus == null ? SessionStatus.OPEN : sessionStatus;
        this.sessionRecruitStatus = sessionRecruitStatus;
        this.maxUserCount = maxUserCount;
        this.sessionPeriod = sessionPeriod;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void register(NsUser user) {
        if (this.sessionRecruitStatus.isNotRecruiting()) {
            throw new IllegalArgumentException("강의가 모집중이지 않습니다.");
        }

        if (this.sessionStatus.isClose()) {
            throw new IllegalArgumentException("강의가 종료되었습니다.");
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

    public SessionBillType getSessionType() {
        return sessionBillType;
    }

    public SessionCoverImage getCoverImageUrl() {
        return sessionCoverImage;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public SessionRecruitStatus getSessionRecruitStatus() {
        return sessionRecruitStatus;
    }

    public int getMaxUserCount() {
        return maxUserCount;
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
        sessionJoins.add(new SessionJoin(this, nsUser, now, now));
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
        return "Session{" + "id=" + id + ", sessionBillType=" + sessionBillType + ", sessionStatus=" + sessionStatus + ", sessionCoverImage=" + sessionCoverImage + ", maxUserCount=" + maxUserCount + ", sessionPeriod=" + sessionPeriod + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
}
