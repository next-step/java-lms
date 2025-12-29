package nextstep.courses.domain;

import nextstep.courses.domain.enrollment.*;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionDuration;
import nextstep.courses.domain.session.SessionState;
import nextstep.courses.domain.session.cover.CoverImage;

import java.time.LocalDateTime;

public class SessionTestBuilder {

    private long id = 1L;

    private long courseId = 10L;

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

    public SessionTestBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public SessionTestBuilder withCourseId(long courseId) {
        this.courseId = courseId;
        return this;
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
            enrollments.enroll(this.id, (long) i + 1);
        }
        return this;
    }

    public Session build() {
        return new Session(
                id,
                courseId,
                sessionDuration,
                coverImage,
                enrollmentPolicy,
                sessionState,
                enrollments
        );
    }
}

