package nextstep.courses.domain.session;

import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.EnrollmentPolicy;
import nextstep.courses.domain.enrollment.Enrollments;
import nextstep.courses.domain.session.cover.CoverImages;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class Session {
    private final long id;
    private final long courseId;
    private final SessionDuration sessionDuration;
    private final CoverImages coverImages;
    private final EnrollmentPolicy enrollmentPolicy;
    private final SessionState sessionState;
    private final Enrollments enrollments;
    private final SessionStstus sessionStstus;
    private final SessionProgress sessionProgress;
    private final EnrollmentAvailabilityPolicy enrollmentAvailabilityPolicy;

    public Session(long id
            , long courseId
            , LocalDateTime startDate
            , LocalDateTime endDate
            , CoverImages coverImages
            , EnrollmentPolicy enrollmentPolicy
            , SessionState sessionState
            , Enrollments enrollments
            , SessionStstus sessionStstus
            , SessionProgress sessionProgress
            , EnrollmentAvailabilityPolicy enrollmentAvailabilityPolicy) {
        this(id, courseId, new SessionDuration(startDate, endDate), coverImages
                , enrollmentPolicy, sessionState, enrollments, sessionStstus, sessionProgress, enrollmentAvailabilityPolicy);
    }

    public Session(long id, long courseId, SessionDuration sessionDuration, CoverImages coverImages
            , EnrollmentPolicy enrollmentPolicy, SessionState sessionState, Enrollments enrollments
            , SessionStstus sessionStstus, SessionProgress sessionProgress, EnrollmentAvailabilityPolicy enrollmentAvailabilityPolicy) {
        this.id = id;
        this.courseId = courseId;
        this.sessionDuration = sessionDuration;
        this.coverImages = coverImages;
        this.enrollmentPolicy = enrollmentPolicy;
        this.sessionState = sessionState;
        this.enrollments = enrollments;
        this.sessionStstus = sessionStstus;
        this.sessionProgress = sessionProgress;
        this.enrollmentAvailabilityPolicy = enrollmentAvailabilityPolicy;
    }

    public Enrollment enroll(Long userId, Payment payment) {
        sessionState.validateEnroll(); // 삭제 예정
        enrollmentAvailabilityPolicy.validate(sessionProgress, sessionStstus);
        enrollmentPolicy.validateEnrollment(payment);
        return enrollments.enroll(this.id, userId);
    }

    public long getId() {
        return id;
    }

    public long getCourseId() {
        return courseId;
    }

    public LocalDateTime getStartDate() {
        return sessionDuration.getStartDate();
    }

    public LocalDateTime getEndDate() {
        return sessionDuration.getEndDate();
    }

    public CoverImages getCoverImages() {
        return coverImages;
    }

    public String getPolicyType() {
        return enrollmentPolicy.type().name();
    }

    public long getPrice() {
        return enrollmentPolicy.price();
    }

    public String getState() {
        return sessionState.name();
    }

    public int getCapacity() {
        return enrollments.getCapacity();
    }

    public String getEnrollmentStatus() {
        return sessionStstus.name();
    }

    public String getSessionProgress() {
        return sessionProgress.name();
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", sessionDuration=" + sessionDuration +
                ", coverImages=" + coverImages +
                ", enrollmentPolicy=" + enrollmentPolicy +
                ", sessionState=" + sessionState +
                ", enrollments=" + enrollments +
                '}';
    }
}
