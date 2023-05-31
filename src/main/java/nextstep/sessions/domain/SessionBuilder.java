package nextstep.sessions.domain;

import java.time.LocalDateTime;

public class SessionBuilder {

    private Long id;
    private SessionDuration duration;
    private SessionCoverImage coverImage;
    private SessionPaymentType paymentType;
    private SessionRegistration registration;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private SessionBuilder() {
    }

    private SessionBuilder(SessionBuilder copy) {
        this.id = copy.id;
        this.duration = copy.duration;
        this.coverImage = copy.coverImage;
        this.paymentType = copy.paymentType;
        this.registration = copy.registration;
        this.createdAt = copy.createdAt;
        this.updatedAt = copy.updatedAt;
    }

    public static SessionBuilder aSession() {
        return new SessionBuilder();
    }

    public SessionBuilder but() {
        return new SessionBuilder(this);
    }

    public SessionBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SessionBuilder withDuration(SessionDuration duration) {
        this.duration = duration;
        return this;
    }

    public SessionBuilder withCoverImage(SessionCoverImage coverImage) {
        this.coverImage = coverImage;
        return this;
    }

    public SessionBuilder withPaymentType(SessionPaymentType paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public SessionBuilder withRegistration(SessionRegistration registration) {
        this.registration = registration;
        return this;
    }

    public SessionBuilder with(SessionRegistrationBuilder sessionRegistrationBuilder) {
        this.registration = sessionRegistrationBuilder.build();
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
        return new Session(id, duration, coverImage, paymentType, registration, createdAt, updatedAt);
    }

}
