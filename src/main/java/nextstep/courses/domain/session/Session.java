package nextstep.courses.domain.session;


import java.time.LocalDateTime;
import java.util.Objects;

public class Session {
    private Long id;
    private Long courseId;
    private SessionPeriod sessionPeriod;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CoverImage coverImage;
    private PaymentType paymentType;
    private SessionEnrollment sessionEnrollment;

    public Session(Long courseId, CoverImage coverImage) {
        this(courseId, coverImage, new SessionEnrollment());
    }

    public Session(Long id, Long courseId, SessionPeriod sessionPeriod, LocalDateTime createdAt, LocalDateTime updatedAt, CoverImage coverImage, PaymentType paymentType, SessionEnrollment sessionEnrollment) {
        this.id = id;
        this.courseId = courseId;
        this.sessionPeriod = sessionPeriod;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.coverImage = coverImage;
        this.paymentType = paymentType;
        this.sessionEnrollment = sessionEnrollment;
    }

    public Session(Long courseId, CoverImage coverImage, SessionEnrollment sessionEnrollment) {
        this(0L, courseId, SessionPeriod.newInstance(), LocalDateTime.now(), null, coverImage, PaymentType.FREE, sessionEnrollment);
    }

    public void register(SessionUser user) {
        sessionEnrollment.enroll(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(courseId, session.courseId) && Objects.equals(sessionPeriod, session.sessionPeriod) && Objects.equals(createdAt, session.createdAt) && Objects.equals(updatedAt, session.updatedAt) && Objects.equals(coverImage, session.coverImage) && paymentType == session.paymentType && Objects.equals(sessionEnrollment, session.sessionEnrollment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, sessionPeriod, createdAt, updatedAt, coverImage, paymentType, sessionEnrollment);
    }

    public Long getId() {
        return id;
    }
    public Long getCourseId() {
        return courseId;
    }
    public LocalDateTime getStartAt() {
        return sessionPeriod.getStartAt();
    }
    public LocalDateTime getEndAt() {
        return sessionPeriod.getEndAt();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getCoverImagePath() {
        return coverImage.getPath();
    }

    public String getCoverImageName() {
        return coverImage.getName();
    }

    public String getPaymentType() {
        return paymentType.name();
    }

    public String getSessionStatus() {
        return sessionEnrollment.getSessionStatus();
    }

    public int getMaxUserSize() {
        return sessionEnrollment.getMaxUserSize();
    }
}
