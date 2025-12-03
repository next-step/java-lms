package nextstep.courses.domain.session.builder;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.session.*;
import nextstep.courses.domain.session.constant.SessionStatus;

import java.time.LocalDateTime;


public class SessionBuilder {

    private Long id = 23334L;
    private Course course = new Course("TDD, 클린 코드 with Java", 1L);
    private CoverImage coverImage = new CoverImage(2L, "png", 300, 200);
    private SessionRange sessionRange = new SessionRangeBuilder().build();
    private SessionPolicy sessionPolicy = new SessionPolicyBuilder().build();
    private SessionStatus sessionStatus = SessionStatus.PENDING;
    private Enrollments enrollments = new Enrollments();
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    public SessionBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SessionBuilder withCourse(Course course) {
        this.course = course;
        return this;
    }

    public SessionBuilder withCoverImage(CoverImage coverImage) {
        this.coverImage = coverImage;
        return this;
    }

    public SessionBuilder withSessionRange(SessionRange sessionRange) {
        this.sessionRange = sessionRange;
        return this;
    }

    public SessionBuilder withSessionPolicy(SessionPolicy sessionPolicy) {
        this.sessionPolicy = sessionPolicy;
        return this;
    }

    public SessionBuilder withSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
        return this;
    }

    public SessionBuilder withEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
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
        return new Session(id, course, sessionRange, sessionPolicy, sessionStatus, coverImage, enrollments, createdAt, updatedAt);
    }

}
