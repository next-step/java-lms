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

    private final SessionStatus sessionStatus;

    private final SessionCoverImage sessionCoverImage;

    private final int maxUserCount;

    private final SessionPeriod sessionPeriod;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public Session(Long id, SessionBillType sessionBillType, SessionStatus sessionStatus, SessionCoverImage sessionCoverImage, int maxUserCount, SessionPeriod sessionPeriod, LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (sessionBillType == null) {
            throw new IllegalArgumentException("과금 유형을 선택해주세요");
        }

        if (sessionPeriod == null) {
            throw new IllegalArgumentException("강의 기간을 설정해주세요");
        }

        this.id = id;
        this.sessionBillType = sessionBillType;
        this.sessionStatus = sessionStatus == null ? SessionStatus.OPEN : sessionStatus;
        this.sessionCoverImage = sessionCoverImage;
        this.maxUserCount = maxUserCount;
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

        sessionJoins.add(new SessionJoin(this, user, LocalDateTime.now(), LocalDateTime.now()));
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

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public SessionCoverImage getCoverImageUrl() {
        return sessionCoverImage;
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
        sessionJoins.add(new SessionJoin(this, nsUser, LocalDateTime.now(), LocalDateTime.now()));
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
}
