package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Session {

    private final SessionDuration duration;
    private final SessionCoverImage coverImage;
    private final SessionPaymentType paymentType;
    private final SessionRegistration registration;

    public Session(SessionDuration duration,
                   SessionCoverImage coverImage,
                   SessionPaymentType paymentType,
                   SessionRegistration registration) {
        this.duration = duration;
        this.coverImage = coverImage;
        this.paymentType = paymentType;
        this.registration = registration;
    }

    public void register(NsUser student) {
        registration.register(student);
    }

    public SessionDuration getDuration() {
        return duration;
    }

    public SessionCoverImage getCoverImage() {
        return coverImage;
    }

    public SessionPaymentType getPaymentType() {
        return paymentType;
    }

    public SessionRegistration getRegistration() {
        return registration;
    }

}
