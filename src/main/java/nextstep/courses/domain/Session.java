package nextstep.courses.domain;

import nextstep.common.BaseEntity;

import java.time.LocalDateTime;

public class Session extends BaseEntity {
    private SessionInfo sessionInfo;
    private CoverImage coverImage;
    private SessionDate sessionDate;
    private Price price;

    public Session(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, SessionInfo sessionInfo, CoverImage coverImage, SessionDate sessionDate, Price price) {
        super(id, createdAt, updatedAt);
        this.sessionInfo = sessionInfo;
        this.coverImage = coverImage;
        this.sessionDate = sessionDate;
        this.price = price;
    }

    public void registerSession(int studentsCount) {
        this.sessionInfo.registerSession(studentsCount);
    }

    public boolean isSession(Long sessionId) {
        return super.getId().equals(sessionId);
    }
}
