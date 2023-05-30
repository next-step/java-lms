package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

public class Session {
    private Long id;
    private List<NsUser> users;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String sessionCoverImage;
    private String sessionCostType;
    private String sessionStatus;

    private int maxUserCount;

    public Session(Long id, List<NsUser> users,LocalDateTime startedAt, LocalDateTime endedAt, String sessionCoverImage, String sessionCostType, String sessionStatus, int maxUserCount) {
        this.id = id;
        this.users = users;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.sessionCoverImage = sessionCoverImage;
        this.sessionCostType = sessionCostType;
        this.sessionStatus = sessionStatus;
        this.maxUserCount = maxUserCount;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public List<NsUser> getUsers() {
        return users;
    }

    public String getSessionCoverImage() {
        return sessionCoverImage;
    }

    public String getSessionCostType() {
        return sessionCostType;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }

    public int getMaxUserCount() {
        return maxUserCount;
    }

    public void register(NsUser user) {
        if (!"RECRUITING".equals(this.sessionStatus)) {
            throw  new IllegalArgumentException("해당 강의는 모집중이 아닙니다.");
        }

        if (this.maxUserCount <= users.size()) {
            throw new IllegalArgumentException("최대 수강 인원을 초과했습니다.");
        }

        this.users.add(user);
    }
}
