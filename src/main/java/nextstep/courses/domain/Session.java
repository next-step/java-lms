package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {
    private Long id;
    private Status status = Status.WAITING;
    private Price price;
    private SessionInfo sessionInfo;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private Long creatorId;
    private int fixedNumber;

    public void checkAbleSession() throws CannotEnrollException {
        this.status.confirmRecruiting();
    }
    public void changeStatue(Status status) {
        this.status = status;
    }

    public Session(Long id,
                   Status status,
                   SessionInfo sessionInfo,
                   LocalDateTime startedAt,
                   LocalDateTime endedAt,
                   int fixedNumber) {
        this.id = id;
        this.status = status;
        this.sessionInfo = sessionInfo;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.fixedNumber = fixedNumber;
    }
}
