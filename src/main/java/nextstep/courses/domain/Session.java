package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

public class Session {
    private Long id;
    private List<NsUser> users;

    private SessionType sessionType;

    private SessionStatus sessionStatus;

    private String coverImageUrl;

    private int maxUserCount;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    public Session() {
    }

    public Session(Long id, List<NsUser> users, SessionType sessionType, SessionStatus sessionStatus, String coverImageUrl, int maxUserCount, LocalDateTime startedAt, LocalDateTime endedAt) {
        this.id = id;
        this.users = users;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
        this.coverImageUrl = coverImageUrl;
        this.maxUserCount = maxUserCount;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public void register(NsUser user) {
        if (!this.sessionStatus.isOpen()) {
            throw new IllegalArgumentException("수강신청은 모집중일때만 등록이 가능합니다.");
        }

        if (maxUserCount <= users.size()) {
            throw new IllegalArgumentException("최대 수강인원을 초과하였습니다.");
        }

        users.add(user);
    }

    public Long getId() {
        return id;
    }

    public List<NsUser> getUsers() {
        return users;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public int getMaxUserCount() {
        return maxUserCount;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }
}
