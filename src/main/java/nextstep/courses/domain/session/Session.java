package nextstep.courses.domain.session;

import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.EnrollmentPolicy;
import nextstep.courses.domain.enrollment.Enrollments;
import nextstep.courses.domain.session.cover.CoverImage;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class Session {
    private final long id;
    private final long courseId;
    private final SessionDuration sessionDuration;
    private final CoverImage coverImage;
    private final EnrollmentPolicy enrollmentPolicy;
    private final SessionState sessionState;
    private final Enrollments enrollments;

    public Session(long id
            , long courseId
            , LocalDateTime startDate
            , LocalDateTime endDate
            , int size
            , String fileName
            , int width
            , int height
            , EnrollmentPolicy enrollmentPolicy
            , SessionState sessionState
            , Enrollments enrollments) {

        this(id, courseId, new SessionDuration(startDate, endDate), new CoverImage(size, fileName, width, height)
                , enrollmentPolicy, sessionState, enrollments);
    }

    public Session(long id, long courseId, SessionDuration sessionDuration, CoverImage coverImage
            , EnrollmentPolicy enrollmentPolicy, SessionState sessionState, Enrollments enrollments) {
        this.id = id;
        this.courseId = courseId;
        this.sessionDuration = sessionDuration;
        this.coverImage = coverImage;
        this.enrollmentPolicy = enrollmentPolicy;
        this.sessionState = sessionState;
        this.enrollments = enrollments;
    }

    public Enrollment enroll(Long userId, Payment payment) {
        sessionState.validateEnroll();
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

    public int getCoverImageSize() {
        return coverImage.getImageSize();
    }

    public String getCoverImageName() {
        return coverImage.getImageName();
    }

    public int getCoverImageWidth() {
        return coverImage.getCoverImageWidth();
    }

    public int getCoverImageHeight() {
        return coverImage.getCoverImageHeight();
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

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", sessionDuration=" + sessionDuration +
                ", coverImage=" + coverImage +
                ", enrollmentPolicy=" + enrollmentPolicy +
                ", sessionState=" + sessionState +
                ", enrollments=" + enrollments +
                '}';
    }
}
