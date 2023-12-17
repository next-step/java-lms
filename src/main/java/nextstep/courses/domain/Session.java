package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

public class Session extends AuditInfo {
    private Long id;
    private Course course;
    private SessionPayment sessionPayment;
    private Enrollment enrollment;
    private Duration duration;
    private SessionStatus sessionStatus;
    private CoverImage coverImage;

    public Session() {
        super(LocalDateTime.now(), LocalDateTime.now());
        this.id = 0L;
        this.course = new Course();
        this.duration = new Duration(LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
        this.sessionPayment = new SessionPayment(SessionPaymentType.FREE, 0L);
        this.enrollment = new Enrollment(new NsUsers(new ArrayList<>()), new NsUserLimit(0, SessionPaymentType.PAID));
        this.sessionStatus = SessionStatus.READY;
    }

    public Session(Long id, Course course,Long amountOfPrice, SessionPaymentType sessionPaymentType, NsUsers nsUsers,
                   Integer limitOfUserCount, Duration duration, SessionStatus sessionStatus ,CoverImage coverImage) {
        super(LocalDateTime.now(), LocalDateTime.now());
        this.id = id;
        this.course = course;
        this.duration = duration;
        this.sessionPayment = new SessionPayment(sessionPaymentType, amountOfPrice);
        this.enrollment = new Enrollment(nsUsers, new NsUserLimit(limitOfUserCount, sessionPaymentType));
        this.sessionStatus = sessionStatus;
        this.coverImage = coverImage;
    }

    public void enroll(NsUser user) {
        if (sessionStatus != SessionStatus.ENROLLING) {
            throw new IllegalArgumentException(ExceptionMessage.SESSION_STATUS_NOT_ENROLLING.getMessage());
        }
        enrollment.enroll(user);
        if (sessionPayment.isPaid() && enrollment.isFull()) {
            sessionStatus = SessionStatus.DONE;
        }
    }

    public boolean isSameId(Long id) {
        return this.id.equals(id);
    }
}
