package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {
    private Long id;
    private Long courseId;
    private SessionPeriod period;
    private String coverImage;
    private boolean isFree;
    private Enrollment enrollment;

    public Session(Long id, Long courseId, SessionPeriod period, String coverImage, boolean isFree, Enrollment enrollment) {
        this.id = id;
        this.courseId = courseId;
        this.period = period;
        this.coverImage = coverImage;
        this.isFree = isFree;
        this.enrollment = enrollment;
    }

    public Session(Long id, Long courseId, LocalDateTime startDate, LocalDateTime endDate, String coverImage, boolean isFree, SessionStatus sessionStatus, Long nsUserId, int maxUserCount) {
        this(id, courseId, new SessionPeriod(startDate, endDate), coverImage, isFree, new Enrollment(sessionStatus, new NsUsers(nsUserId), maxUserCount));
    }

    public Session(Long id, Long courseId, LocalDateTime startDate, LocalDateTime endDate, String coverImage, boolean isFree, SessionStatus sessionStatus, NsUsers nsUsers, int maxUserCount) {
        this(id, courseId, new SessionPeriod(startDate, endDate), coverImage, isFree, new Enrollment(sessionStatus, nsUsers, maxUserCount));
    }


    public Session enrollNsUser(NsUser nsUser) {
        enrollment.enrollNsUser(nsUser);
        return this;
    }

    public Long getId() {
        return id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public SessionPeriod getPeriod() {
        return period;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public boolean isFree() {
        return isFree;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public static class Builder {
        private Long id;
        private Long courseId;
        private SessionPeriod period;
        private String coverImage;
        private boolean isFree;
        private Enrollment enrollment;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder with(Session session) {
            this.courseId = session.getCourseId();
            this.period = session.getPeriod();
            this.coverImage = session.getCoverImage();
            this.isFree = session.isFree;
            this.enrollment = session.getEnrollment();
            return this;
        }

        public Session build() {
            return new Session(id, courseId, period, coverImage, isFree, enrollment);
        }
    }
}
