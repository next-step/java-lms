package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class Session {
    private final long id;
    private final SessionDuration sessionDuration;
    private final CoverImage coverImage;
    private final EnrollmentPolicy enrollmentPolicy;
    private final SessionState sessionState;
    private final Enrollments enrollments;

    public Session(long id
            , LocalDateTime startDate
            , LocalDateTime endDate
            , int size
            , String fileName
            , int width
            , int height
            , EnrollmentPolicy enrollmentPolicy
            , SessionState sessionState
            , Enrollments enrollments) {
        this(id, new SessionDuration(startDate, endDate), new CoverImage(size, fileName, width, height)
                , enrollmentPolicy, sessionState, enrollments);
    }

    public Session(long id, SessionDuration sessionDuration, CoverImage coverImage
            , EnrollmentPolicy enrollmentPolicy, SessionState sessionState, Enrollments enrollments) {
        this.id = id;
        this.sessionDuration = sessionDuration;
        this.coverImage = coverImage;
        this.enrollmentPolicy = enrollmentPolicy;
        this.sessionState = sessionState;
        this.enrollments = enrollments;
    }

    public Enrollment enroll(Long userId, Payment payment) {
        sessionState.validateEnroll();
        enrollmentPolicy.validateEnrollment(payment);
        return enrollments.add(this.id, userId);
    }

    public long getId() {
        return id;
    }
}
