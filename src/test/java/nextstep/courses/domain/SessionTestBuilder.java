package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionTestBuilder {

    private long id = 1L;

    private SessionDuration sessionDuration = new SessionDuration(
                    LocalDateTime.now().plusDays(1),
                    LocalDateTime.now().plusDays(2)
            );

    private CoverImage coverImage = new CoverImage(100, "test.png", 300, 200);

    private EnrollmentPolicy enrollmentPolicy = new FreeEnrollmentPolicy();

    private SessionState sessionState = SessionState.OPEN;

    private Enrollments enrollments = new Enrollments(new Capacity(1));

    public static SessionTestBuilder aSession() {
        return new SessionTestBuilder();
    }

    public SessionTestBuilder withClosedSession() {
        this.sessionState = SessionState.CLOSED;
        return this;
    }

    public SessionTestBuilder withPaidEnrollment(Money price) {
        this.enrollmentPolicy = new PaidEnrollmentPolicy(price);
        return this;
    }

    public SessionTestBuilder withCapacity(int max) {
        this.enrollments = new Enrollments(new Capacity(max));
        return this;
    }

    public SessionTestBuilder withFullEnrollments(int count) {
        for (int i = 0; i < count; i++) {
            enrollments.add(this.id, (long) i + 1);
        }
        return this;
    }

    public Session build() {
        return new Session(
                id,
                sessionDuration,
                coverImage,
                enrollmentPolicy,
                sessionState,
                enrollments
        );
    }
}

