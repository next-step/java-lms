package nextstep.sessions.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import nextstep.courses.domain.Course;
import nextstep.enrollment.domain.Enrollment;

public class Session {

    private Long id;

    private int maxEnrollment;

    private SessionStatus sessionStatus;

    private SessionPrice sessionPrice;

    private SessionPeriod sessionPeriod;

    private Course course;

    private List<Enrollment> enrollments = new ArrayList<>();

    public Session(final int maxEnrollment, final SessionStatus sessionStatus, final long price,
                   final LocalDateTime endAt, final Course course) {
        this(maxEnrollment, sessionStatus, price, LocalDateTime.now(), endAt, course);
    }

    public Session(final int maxEnrollment, final SessionStatus sessionStatus, final long price,
                   final LocalDateTime startedAt, final LocalDateTime endAt, final Course course) {
        this.maxEnrollment = maxEnrollment;
        this.sessionStatus = sessionStatus;
        this.sessionPrice = new SessionPrice(price);
        this.sessionPeriod = new SessionPeriod(startedAt, endAt);
        this.course = course;
    }

    public static Session free(final SessionStatus sessionStatus, final Course course) {
        return new Session(Integer.MAX_VALUE, sessionStatus, 0L, LocalDateTime.now(), LocalDateTime.MAX, course);
    }

    public boolean isFull() {
        return enrollments.size() == maxEnrollment;
    }

    public boolean isNotRecruiting() {
        return sessionStatus.isNotRecruiting();
    }

    public void validatePriceEquality(final long price) {
        sessionPrice.validatePriceEquality(price);
    }

    public void validateNonFreeSession() {
        if (sessionPrice.isNotFree()) {
            sessionPeriod.validateStartedAt();
        }
    }

    public boolean canEnroll(final Enrollment enrollment) {
        return enrollments.contains(enrollment);
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }
}
