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
        this.period = period;
        this.coverImage = coverImage;
        this.isFree = isFree;
        this.enrollment = enrollment;
    }

    public Session(Long id, Long courseId, LocalDateTime startDate, LocalDateTime endDate, String coverImage, boolean isFree, SessionStatus sessionStatus, NsUsers nsUsers) {
        this(id, courseId, new SessionPeriod(startDate, endDate), coverImage, isFree, new Enrollment(sessionStatus, nsUsers));
    }


    public Session enrollNsUser(NsUser nsUser) {
        enrollment.enrollNsUser(nsUser);
        return this;
    }


    public int countNsUsers() {
        return enrollment.countNsUsers();
    }
}
