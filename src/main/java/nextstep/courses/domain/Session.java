package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Session {
    private final Long id;
    private final ImageFile imageFile;
    private final SessionPeriod period;
    private final SessionStatus sessionStatus;
    private final EnrollmentRule enrollmentRule;
    private final Enrollments enrollments;

    public Session(Long id,ImageFile imageFile, LocalDateTime startTime, LocalDateTime endTime, String sessionStatus, Integer price, Integer capacity) {
        this(id, imageFile, new SessionPeriod(startTime, endTime), SessionStatus.valueOf(sessionStatus), allocateEnrollmentRule(price, capacity), new Enrollments());
    }

    public Session(ImageFile imageFile, SessionPeriod period, SessionStatus sessionStatus, EnrollmentRule enrollmentRule) {
        this(null,  imageFile, period, sessionStatus, enrollmentRule, new Enrollments());
    }

    public Session(ImageFile imageFile, SessionPeriod period, SessionStatus sessionStatus, EnrollmentRule enrollmentRule, Enrollments enrollments) {
        this(null,  imageFile, period, sessionStatus, enrollmentRule, enrollments);
    }

    public Session(Long id, ImageFile imageFile, SessionPeriod period, SessionStatus sessionStatus, EnrollmentRule enrollmentRule, Enrollments enrollments) {
        this.id = id;
        this.imageFile = imageFile;
        this.period = period;
        this.sessionStatus = sessionStatus;
        this.enrollmentRule = enrollmentRule;
        this.enrollments = enrollments;
    }

    public int countEnrollments() {
        return enrollments.countEnrollments();
    }

    public void enroll(Enrollment enrollment, Money money) {
        validationRecruiting();

        enrollment.validateBelongsTo(getId());

        enrollmentRule.validate(money, countEnrollments());

        enrollments.enroll(enrollment);
    }

    public Long getId() {
        return id;
    }

    public Long getImageId() {
        return this.imageFile.getImageId();
    }

    public String getSessionStatus() {
        return this.sessionStatus.toString();
    }

    public Integer getPrice() {
        if (enrollmentRule.getType().equals(SessionType.PAID)) {
            return ((PaidEnrollmentRule) this.enrollmentRule).getPrice();
        }

        return null;
    }

    public Integer getCapacity() {
        if (enrollmentRule.getType().equals(SessionType.PAID)) {
            return ((PaidEnrollmentRule) this.enrollmentRule).getCapacity();
        }

        return null;
    }

    public LocalDateTime getStartTime() {
        return this.period.getStartTime();
    }

    public LocalDateTime getEndTime() {
        return this.period.getEndTime();
    }

    public SessionPeriod getPeriod() {
        return this.period;
    }

    private static EnrollmentRule allocateEnrollmentRule(Integer price, Integer capacity) {
        if (price != null) {
            return new PaidEnrollmentRule(price, capacity);
        }

        return new FreeEnrollmentRule();
    }

    private void validationRecruiting() {
        if (!sessionStatus.enableRecruiting()) {
            throw new IllegalArgumentException("모집중인 강의만 수강 신청할 수 있습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(imageFile, session.imageFile) && Objects.equals(period, session.period) && sessionStatus == session.sessionStatus && Objects.equals(enrollmentRule, session.enrollmentRule) && Objects.equals(enrollments, session.enrollments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageFile, period, sessionStatus, enrollmentRule, enrollments);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", imageFile=" + imageFile +
                ", period=" + period +
                ", sessionStatus=" + sessionStatus +
                ", enrollmentRule=" + enrollmentRule +
                ", enrollments=" + enrollments +
                '}';
    }
}
