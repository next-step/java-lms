package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionBuilder {
    private Long id = 1L;
    private ImageFile imageFile = new ImageFile(1024 * 1024, "png", 300 , 200);
    private SessionPeriod period =
            new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7));
    private SessionStatus sessionStatus = SessionStatus.RECRUITING;
    private EnrollmentRule enrollmentRule = new PaidEnrollmentRule(50000, 10);
    private Enrollments enrollments = new Enrollments();

    public static SessionBuilder aRecuitingSession() {
        return new SessionBuilder();
    }

    public static SessionBuilder anEndSession() {
        return new SessionBuilder()
                .withStatus(SessionStatus.END);
    }

    public SessionBuilder withStatus(SessionStatus status) {
        this.sessionStatus = status;
        return this;
    }

    public SessionBuilder asPaidSession(int price, int capacity) {
        this.enrollmentRule = new PaidEnrollmentRule(price, capacity);
        return this;
    }

    public SessionBuilder asFreeSession() {
        this.enrollmentRule = new FreeEnrollmentRule();
        return this;
    }

    public Session build() {
        return new Session(id, imageFile, period, sessionStatus, enrollmentRule, enrollments);
    }

}
