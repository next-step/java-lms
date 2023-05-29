package nextstep.courses.domain;

public class SessionBuilder {

    private SessionDuration duration;
    private SessionCoverImage coverImage;
    private SessionPaymentType paymentType;
    private SessionRegistration registration;

    private SessionBuilder() {
    }

    private SessionBuilder(SessionBuilder copy) {
        this.duration = copy.duration;
        this.coverImage = copy.coverImage;
        this.paymentType = copy.paymentType;
        this.registration = copy.registration;
    }

    public static SessionBuilder aSession() {
        return new SessionBuilder();
    }

    public SessionBuilder but() {
        return new SessionBuilder(this);
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

    public Session build() {
        return new Session(duration, coverImage, paymentType, registration);
    }

}
