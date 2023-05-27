package nextstep.courses.domain;

import java.time.LocalDateTime;
import nextstep.users.domain.NsUser;

public class Session {

    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private CoverImage coverImage;

    private boolean isFree;

    private SessionStatus sessionStatus;

    private SessionPersonnel sessionPersonnel;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    public Session(Long id, LocalDateTime startTime, LocalDateTime endTime, CoverImage coverImage,
        boolean isFree, SessionStatus sessionStatus, int capacity) {
        this(id, startTime, endTime, coverImage, isFree, sessionStatus,
            new SessionPersonnel(capacity));
    }

    public Session(Long id, LocalDateTime startTime, LocalDateTime endTime, CoverImage coverImage,
        boolean isFree, SessionStatus sessionStatus, SessionPersonnel sessionPersonnel) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.coverImage = coverImage;
        this.isFree = isFree;
        this.sessionStatus = sessionStatus;
        this.sessionPersonnel = sessionPersonnel;
    }

    public Long register(NsUser user, LocalDateTime registerTime) {
        if (registerTime.isBefore(startTime) || registerTime.isAfter(endTime)) {
            throw new RuntimeException("강의 신청 기간이 아닙니다.");
        }
        if (!sessionStatus.equals(SessionStatus.Recruiting)) {
            throw new IllegalStateException("모집중이 아닙니다.");
        }
        sessionPersonnel.register(user);
        return id;
    }

}
