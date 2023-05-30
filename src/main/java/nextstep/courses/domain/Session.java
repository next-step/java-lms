package nextstep.courses.domain;

import java.time.LocalDateTime;
import nextstep.users.domain.NsUser;

public class Session {

    private Long id;

    private SessionPeriod sessionPeriod;

    private String coverImage;

    private boolean isFree;

    private SessionStatus sessionStatus;

    private SessionPersonnel sessionPersonnel;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    public Session(Long id, SessionPeriod sessionPeriod, String coverImage,
        boolean isFree, SessionStatus sessionStatus, int capacity) {
        this(id, sessionPeriod, coverImage, isFree, sessionStatus,
            new SessionPersonnel(capacity));
    }

    public Session(Long id, SessionPeriod sessionPeriod, String coverImage,
        boolean isFree, SessionStatus sessionStatus, SessionPersonnel sessionPersonnel) {
        this.id = id;
        this.sessionPeriod = sessionPeriod;
        this.coverImage = coverImage;
        this.isFree = isFree;
        this.sessionStatus = sessionStatus;
        this.sessionPersonnel = sessionPersonnel;
    }

    public Long register(NsUser user, LocalDateTime registerTime) {
        validRegister(registerTime);
        sessionPersonnel.register(user);
        return id;
    }

    private void validRegister(LocalDateTime registerTime) {
        if (sessionPeriod.isNotBetween(registerTime)) {
            throw new RuntimeException("강의 신청 기간이 아닙니다.");
        }
        if (!sessionStatus.equals(SessionStatus.Recruiting)) {
            throw new IllegalStateException("모집중이 아닙니다.");
        }
    }

}
