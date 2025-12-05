package nextstep.courses.domain.session;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.session.constant.SessionStatus;
import nextstep.courses.domain.session.constant.SessionType;
import nextstep.courses.record.SessionRecord;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Session extends SessionCore {

    private final Long id;
    private Course course;
    private final CoverImage coverImage;
    private final Enrollments enrollments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate, String sessionType, String sessionStatus, CoverImage coverImage) {
        this(id, startDate, endDate, sessionType, Integer.MAX_VALUE, 0L, sessionStatus, coverImage);
    }

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate, String sessionType, int maxCapacity, Long tuition, String sessionStatus, CoverImage coverImage) {
        this(id, startDate, endDate, SessionType.from(sessionType.toUpperCase()), maxCapacity, tuition, SessionStatus.from(sessionStatus.toUpperCase()), coverImage);
    }

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate, SessionType sessionType, int maxCapacity, Long tuition, SessionStatus sessionStatus, CoverImage coverImage) {
        this(id, new SessionRange(startDate, endDate), sessionType, new Capacity(maxCapacity), new Tuition(tuition), sessionStatus, coverImage);
    }

    public Session(Long id, SessionRange sessionRange, SessionType sessionType, Capacity maxCapacity, Tuition tuition, SessionStatus sessionStatus, CoverImage coverImage) {
        this(id, sessionRange, new SessionPolicy(maxCapacity, tuition, sessionType), sessionStatus, coverImage);
    }

    public Session(Long id, SessionRange sessionRange, SessionPolicy sessionPolicy, SessionStatus sessionStatus, CoverImage coverImage) {
        this(id, null, sessionRange, sessionPolicy, sessionStatus, coverImage, new Enrollments(), LocalDateTime.now(), null);
    }

    public Session(Long id, Course course, SessionRange sessionRange, SessionPolicy sessionPolicy, SessionStatus sessionStatus, CoverImage coverImage, Enrollments enrollments) {
        this(id, course, sessionRange, sessionPolicy, sessionStatus, coverImage, enrollments, LocalDateTime.now(), null);
    }

    public Session(Long id, Course course, SessionRange sessionRange, SessionPolicy sessionPolicy, SessionStatus sessionStatus, CoverImage coverImage, Enrollments enrollments, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(sessionRange, sessionPolicy, sessionStatus);
        this.id = id;
        this.course = course;
        this.coverImage = coverImage;
        this.enrollments = enrollments;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void addEnrollment(Enrollment enrollment, Payment payment) {
        validatePaymentAmount(payment);
        validateNotFull(this.enrollments);
        validateSessionStatus();
        this.enrollments.add(enrollment);
    }

    public Long getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public CoverImage getCoverImage() {
        return coverImage;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments.getEnrollments();
    }

    public SessionRecord toSessionRecord() {
        return new SessionRecord(
                this.id,
                this.course.getId(),
                this.coverImage.getId(),
                this.getSessionRange().getStartDate(),
                this.getSessionRange().getEndDate(),
                this.getMaxCapacity(),
                this.getTuition(),
                this.getSessionType(),
                this.getSessionStatus().toString(),
                this.createdAt,
                this.updatedAt
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(coverImage, session.coverImage) && Objects.equals(enrollments, session.enrollments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, coverImage, enrollments);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", coverImage=" + coverImage +
                ", enrollments=" + enrollments +
                '}';
    }


}
