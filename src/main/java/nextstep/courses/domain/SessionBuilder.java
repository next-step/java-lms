package nextstep.courses.domain;

import java.time.LocalDateTime;

public final class SessionBuilder {
    private Long id;
    private SessionStatus sessionStatus;
    private SessionBillType sessionBillType;
    private SessionCoverImage sessionCoverImage;
    private SessionRegistration sessionRegistration;
    private SessionPeriod sessionPeriod;
    private SessionJoins sessionJoins;
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

    public SessionBuilder withSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
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

    public SessionBuilder withSessionJoins(SessionJoins sessionJoins) {
        this.sessionJoins = sessionJoins;
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
        return new Session(id, sessionStatus, sessionBillType, sessionCoverImage, sessionRegistration, sessionPeriod,
                           sessionJoins, createdAt, updatedAt);
    }
}
