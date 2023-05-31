package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

public class Session {
    private Long id;
    private final List<NsUser> users;
    private final SessionPeriod sessionPeriod;
    private final String sessionCoverImage;
    private final SessionCostType sessionCostType;
    private final SessionStatus sessionStatus;

    private final int maxUserCount;

    public Session(Long id, List<NsUser> users, SessionPeriod sessionPeriod, String sessionCoverImage, SessionCostType sessionCostType, SessionStatus sessionStatus, int maxUserCount) {
        this.id = id;
        this.users = users;
        this.sessionPeriod = sessionPeriod;
        this.sessionCoverImage = sessionCoverImage;
        this.sessionCostType = sessionCostType;
        this.sessionStatus = sessionStatus;
        this.maxUserCount = maxUserCount;
    }

    public LocalDateTime startedAt() {
        return this.sessionPeriod.getStartedAt();
    }

    public LocalDateTime endedAt() {
        return this.sessionPeriod.getEndedAt();
    }

    public List<NsUser> getUsers() {
        return users;
    }

    public String getSessionCoverImage() {
        return sessionCoverImage;
    }

    public SessionCostType getSessionCostType() {
        return this.sessionCostType;
    }

    public SessionStatus getSessionStatus() {
        return this.sessionStatus;
    }

    public int getMaxUserCount() {
        return maxUserCount;
    }

    public void register(NsUser user) {
        if (!SessionStatus.RECRUITING.equals(this.sessionStatus)) {
            throw  new IllegalArgumentException("해당 강의는 모집중이 아닙니다.");
        }

        if (this.maxUserCount <= users.size()) {
            throw new IllegalArgumentException("최대 수강 인원을 초과했습니다.");
        }

        this.users.add(user);
    }
}
