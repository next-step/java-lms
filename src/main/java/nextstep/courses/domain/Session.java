package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session extends BaseEntity {

    private SessionRegistration sessionRegistration;
    private CoverImage imageUrl;
    private SessionDate sessionDate;
    private Price price;

    public Session(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, SessionRegistration sessionRegistration, CoverImage imageUrl, SessionDate sessionDate, Price price) {
        super(id, createdAt, updatedAt);
        this.sessionRegistration = sessionRegistration;
        this.imageUrl = imageUrl;
        this.sessionDate = sessionDate;
        this.price = price;
    }

    public void registerSession(int studentCount) {
        this.sessionRegistration.register(studentCount);
    }

    public boolean isSession(Long sessionId) {
        return super.getId().equals(sessionId);
    }
}
