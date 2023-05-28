package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session extends BaseEntity {

    private SessionInfo sessionInfo;
    private CoverImage imageUrl;
    private SessionDate sessionDate;
    private Price price;

    public Session(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, SessionInfo sessionInfo, CoverImage imageUrl, SessionDate sessionDate, Price price) {
        super(id, createdAt, updatedAt);
        this.sessionInfo = sessionInfo;
        this.imageUrl = imageUrl;
        this.sessionDate = sessionDate;
        this.price = price;
    }

    public void registerSession(int studentCount) {
        this.sessionInfo.registerSession(studentCount);
    }

    public boolean isSession(Long sessionId) {
        return super.getId().equals(sessionId);
    }
}
