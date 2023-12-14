package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Session {
    private Long id;

    private String title;

    private SessionType sessionType;

    private ImageInfo coverImage;

    private Enrollment enrollment;

    private Integer capacity;

    private Long fee;

    private SessionPeriod sessionPeriod;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Session(long id, SessionType sessionType, Enrollment enrollment, Long fee, SessionPeriod sessionPeriod, Integer capacity) {
        this(id, null, sessionType, enrollment,null, fee, sessionPeriod, capacity);
    }

    public static Session sessionWithImage(long id, ImageInfo imageInfo) {
        Enrollment enrollment = new Enrollment(null);
        return new Session(id, null,null, enrollment, imageInfo, null, new SessionPeriod(LocalDate.now().plusDays(3), LocalDate.now().plusDays(15), SessionState.RECRUITING), null);
    }

    public static Session sessionWithState(long id, Enrollment enrollment, SessionPeriod sessionPeriod) {
        return new Session(id, null,null, enrollment, null, null, sessionPeriod, null);
    }

    public static Session recruitingSessionWithType(long id, SessionType sessionType, Enrollment enrollment, Integer capacity) {
        return new Session(id, sessionType, enrollment, null,  new SessionPeriod(LocalDate.now().plusDays(3), LocalDate.now().plusDays(15), SessionState.RECRUITING), capacity);
    }

    public static Session recruitingPaidSession(long id, SessionType sessionType, Enrollment enrollment, Long fee, Integer capacity) {
        return new Session(id, sessionType, enrollment, fee, new SessionPeriod(LocalDate.now().plusDays(3), LocalDate.now().plusDays(15), SessionState.RECRUITING), capacity);
    }

    public Session(long id, String title, SessionType sessionType, Enrollment enrollment, ImageInfo imageInfo, Long fee, SessionPeriod sessionPeriod, Integer capacity) {
        this.id = id;
        this.title = title;
        this.sessionType = sessionType;
        this.enrollment = enrollment;
        this.fee = fee;
        this.coverImage = imageInfo;
        this.sessionPeriod = sessionPeriod;
        this.capacity = capacity;
        this.creatorId = null;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void enrollStudent(NsUser student, Payment payment) {
        sessionPeriod.checkAbleToEnroll();

        if(sessionType == SessionType.PAID){
            enrollment.isOverCapacity(capacity);
            payment.isAbleToPayment(fee);
        }

        enrollment.enroll(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(title, session.title) && sessionType == session.sessionType && Objects.equals(coverImage, session.coverImage) && Objects.equals(enrollment, session.enrollment) && Objects.equals(fee, session.fee) && Objects.equals(sessionPeriod, session.sessionPeriod) && Objects.equals(creatorId, session.creatorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, sessionType, coverImage, enrollment, fee, sessionPeriod, creatorId);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", sessionType=" + sessionType +
                ", coverImage=" + coverImage +
                ", enrollment=" + enrollment +
                ", fee=" + fee +
                ", sessionPeriod=" + sessionPeriod +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
