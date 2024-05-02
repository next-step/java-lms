package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Session extends BaseTimeEntity {

    private final CoverImage coverImage;
    private final EnrollmentManager enrollmentManager;
    private final SessionPeriod sessionPeriod;
    private final Long courseId;
    private final Long id;


    public Session(Long fee, Integer count, String status, LocalDate startDate, LocalDate endDate, Long courseId) {
        this(LocalDateTime.now(), LocalDateTime.now(), null, new EnrollmentManager(fee, count, status), new SessionPeriod(startDate, endDate), courseId, 0L);
    }

    public Session(LocalDateTime createdAt, LocalDateTime updatedAt, EnrollmentManager enrollmentManager, SessionPeriod sessionPeriod, Long courseId, Long id) {
        this(createdAt, updatedAt, null, enrollmentManager, sessionPeriod, courseId, id);
    }

    public Session(CoverImage coverImage, EnrollmentManager enrollmentManager, SessionPeriod sessionPeriod) {
        this(LocalDateTime.now(), LocalDateTime.now(), coverImage, enrollmentManager, sessionPeriod, null, null);
    }

    public Session(LocalDateTime createdAt, LocalDateTime updatedAt, CoverImage coverImage, Long fee, Integer count, String status, LocalDate startDate, LocalDate endDate, Long courseId, Long id) {
        this(createdAt, updatedAt, coverImage, new EnrollmentManager(fee, count, status), new SessionPeriod(startDate, endDate), courseId, id);
    }

    private Session(LocalDateTime createdAt, LocalDateTime updatedAt, CoverImage coverImage, EnrollmentManager enrollmentManager, SessionPeriod sessionPeriod, Long courseId, Long id) {
        super(createdAt, updatedAt);
        this.id = id;
        this.coverImage = coverImage;
        this.enrollmentManager = enrollmentManager;
        this.sessionPeriod = sessionPeriod;
        this.courseId = courseId;
    }

    public Session enroll(Payment payment) {
        if (this.enrollmentManager.canEnroll(payment)) {
            return new Session(this.coverImage, this.enrollmentManager.decreaseCount(), this.sessionPeriod);
        }
        throw new IllegalArgumentException("수강 신청을 할 수 없습니다.");
    }

    public Long getCourseId() {
        return courseId;
    }

    public CoverImage getCoverImage() {
        return coverImage;
    }

    public EnrollmentManager getEnrollmentManager() {
        return enrollmentManager;
    }

    public SessionPeriod getSessionPeriod() {
        return sessionPeriod;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", coverImage=" + coverImage +
                ", enrollmentManager.fee=" + enrollmentManager.getFee() +
                ", enrollmentManager.count=" + enrollmentManager.getCount() +
                ", enrollmentManager.status=" + enrollmentManager.getStatus() +
                ", sessionPeriod.startDate=" + sessionPeriod.getStartDate() +
                ", sessionPeriod.endDate=" + sessionPeriod.getEndDate() +
                ", courseId=" + courseId +
                '}';
    }
}
