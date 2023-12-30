package nextstep.courses.domain;


import java.time.LocalDateTime;

import java.util.Objects;
import java.util.function.LongFunction;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

public class Session extends AuditInfo{
    private Long id;
    private Course course;
    private SessionPayment sessionPayment;
    private Enrollment enrollment;
    private Duration duration;
    private SessionStatus sessionStatus;
    private CoverImage coverImage;

    public Session(Long id, Course course,Long amountOfPrice, SessionPaymentType sessionPaymentType, NsUsers nsUsers,
                   Integer limitOfUserCount, Duration duration, SessionStatus sessionStatus ,CoverImage coverImage,
                   LocalDateTime createAt, LocalDateTime updateAt) {
        super(createAt, updateAt);
        this.id = id;
        this.course = course;
        this.duration = duration;
        this.sessionPayment = new SessionPayment(sessionPaymentType, amountOfPrice);
        this.enrollment = new Enrollment(nsUsers, new NsUserLimit(limitOfUserCount, sessionPaymentType));
        this.sessionStatus = sessionStatus;
        this.coverImage = coverImage;
    }

    public Long getId() {
        return id;
    }

    public Course getCourse() {
        return new Course(course);
    }

    public SessionPayment getSessionPayment() {
        return new SessionPayment(sessionPayment);
    }

    public Enrollment getEnrollment() {
        return new Enrollment(enrollment);
    }

    public Duration getDuration() {
        return new Duration(duration);
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public CoverImage getCoverImage() {
        return new CoverImage(coverImage);
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

    public void replaceEnrollmentNsUsers(LongFunction<NsUsers> function){
        this.enrollment.replaceUsers(function.apply(id));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Session session = (Session) object;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
