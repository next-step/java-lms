package nextstep.courses.domain;

import nextstep.courses.domain.enrollment.*;
import nextstep.courses.domain.session.*;
import nextstep.courses.domain.session.cover.CoverImage;
import nextstep.courses.domain.session.cover.CoverImages;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SessionTestBuilder {

    private long id = 1L;

    private long courseId = 10L;

    private SessionDuration sessionDuration = new SessionDuration(
                    LocalDateTime.now().plusDays(1),
                    LocalDateTime.now().plusDays(2)
            );

    private CoverImages coverImages = new CoverImages(new ArrayList<>(List.of(new CoverImage(100, "test.png", 300, 200))));

    private EnrollmentPolicy enrollmentPolicy = new FreeEnrollmentPolicy();

    private SessionState sessionState = SessionState.OPEN;

    private Enrollments enrollments = new Enrollments(new Capacity(1));
    private EnrollmentStatus enrollmentStatus = EnrollmentStatus.OPEN;
    private SessionProgress sessionProgress = SessionProgress.IN_PROGRESS;
    private EnrollmentAvailabilityPolicy enrollmentAvailabilityPolicy = new EnrollmentAvailabilityPolicy();

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

    public SessionTestBuilder withClosedEnrollmentStatus() {
        this.enrollmentStatus = EnrollmentStatus.CLOSED;
        return this;
    }

    public SessionTestBuilder withFinishedSession() {
        this.sessionProgress = SessionProgress.FINISHED;
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

    public SessionTestBuilder withAvailabilityPolicy(EnrollmentAvailabilityPolicy policy) {
        this.enrollmentAvailabilityPolicy = policy;
        return this;
    }

    public Session build() {
        return new Session(
                id,
                courseId,
                sessionDuration,
                coverImages,
                enrollmentPolicy,
                sessionState,
                enrollments,
                enrollmentStatus,
                sessionProgress,
                enrollmentAvailabilityPolicy
        );
    }
}

