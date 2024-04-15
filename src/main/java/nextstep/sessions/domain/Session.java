package nextstep.sessions.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import nextstep.courses.domain.Course;
import nextstep.enrollment.domain.Enrollment;

public class Session {

    private Long id;

    private int maxEnrollment;

    private SessionRecruitingStatus sessionRecruitingStatus;

    private SessionProgressStatus sessionProgressStatus;

    private SessionPrice sessionPrice;

    private SessionPeriod sessionPeriod;

    private Course course;

    private List<Enrollment> enrollments = new ArrayList<>();

    public Session(final int maxEnrollment, final SessionRecruitingStatus sessionRecruitingStatus,
                   final SessionProgressStatus sessionProgressStatus, final long price, final LocalDateTime endAt,
                   final Course course) {
        this(maxEnrollment, sessionRecruitingStatus, sessionProgressStatus, price, LocalDateTime.now(), endAt, course);
    }

    public Session(final int maxEnrollment, final SessionRecruitingStatus sessionRecruitingStatus, final SessionProgressStatus sessionProgressStatus,
                   final long price, final LocalDateTime startedAt, final LocalDateTime endAt, final Course course) {
        this(null, maxEnrollment, sessionRecruitingStatus, sessionProgressStatus, price, startedAt, endAt, course);
    }

    public Session(final Long id, final int maxEnrollment, final SessionRecruitingStatus sessionRecruitingStatus,
                   final SessionProgressStatus sessionProgressStatus, final long price,
                   final LocalDateTime startedAt, final LocalDateTime endAt, final Course course) {
        this.id = id;
        this.maxEnrollment = maxEnrollment;
        this.sessionRecruitingStatus = sessionRecruitingStatus;
        this.sessionProgressStatus = sessionProgressStatus;
        this.sessionPrice = new SessionPrice(price);
        this.sessionPeriod = new SessionPeriod(startedAt, endAt);
        this.course = course;
    }

    public static Session free(final SessionRecruitingStatus recruitingStatus, final SessionProgressStatus progressStatus,
                               final Course course) {
        return new Session(Integer.MAX_VALUE, recruitingStatus, progressStatus, 0L, LocalDateTime.now(), LocalDateTime.MAX, course);
    }

    public boolean isFull() {
        return enrollments.size() == maxEnrollment;
    }

    public boolean isNotRecruiting() {
        return sessionRecruitingStatus.isNotRecruiting();
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

    public void addEnrollment(final Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public void removeEnrollment(final Enrollment enrollment) {
        enrollments.remove(enrollment);
    }

    public boolean isEnd() {
        return sessionProgressStatus.isEnd();
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public Long getId() {
        return id;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public String getSessionStatus() {
        return sessionRecruitingStatus.name();
    }

    public String getSessionProgressStatus() {
        return sessionProgressStatus.name();
    }

    public long getSessionPrice() {
        return sessionPrice.getValue();
    }

    public LocalDateTime getSessionStartDate() {
        return sessionPeriod.getStartedAt();
    }

    public LocalDateTime getSessionEndDate() {
        return sessionPeriod.getEndAt();
    }

    public Long getCourse() {
        return course.getId();
    }
}
