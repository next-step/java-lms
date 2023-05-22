package nextstep.courses.domain;

import java.time.LocalDateTime;

public final class SessionBuilder {
    private Long id;
    private SessionBillType sessionBillType;
    private SessionCoverImage sessionCoverImage;
    private SessionRegistration sessionRegistration;
    private SessionPeriod sessionPeriod;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private SessionBuilder() {
    }

    public static SessionBuilder aSession() {
        return new SessionBuilder();
    }

    public SessionBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SessionBuilder withSessionBillType(SessionBillType sessionBillType) {
        this.sessionBillType = sessionBillType;
        return this;
    }

    public SessionBuilder withSessionCoverImage(SessionCoverImage sessionCoverImage) {
        this.sessionCoverImage = sessionCoverImage;
        return this;
    }

    public SessionBuilder withSessionRegistration(SessionRegistration sessionRegistration) {
        this.sessionRegistration = sessionRegistration;
        return this;
    }

    public SessionBuilder withSessionPeriod(SessionPeriod sessionPeriod) {
        this.sessionPeriod = sessionPeriod;
        return this;
    }

    public SessionBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public SessionBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Session build() {
        return new Session(id, sessionBillType, sessionCoverImage, sessionRegistration, sessionPeriod, createdAt,
                           updatedAt);
    }
}
