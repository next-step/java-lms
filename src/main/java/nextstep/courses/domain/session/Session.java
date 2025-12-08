package nextstep.courses.domain.session;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.session.constant.SessionStatus;
import nextstep.courses.domain.session.constant.SessionType;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Session extends BaseEntity {

    private Course course;
    private final CoverImage coverImage;
    private final Enrollments enrollments;
    private SessionCore sessionCore;

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
        this(id, null, coverImage, new Enrollments(), LocalDateTime.now(), null, new SessionCore(sessionRange, sessionPolicy, sessionStatus));
    }

    public Session(Long id, Course course, SessionRange sessionRange, SessionPolicy sessionPolicy, SessionStatus sessionStatus, CoverImage coverImage, Enrollments enrollments) {
        this(id, course, coverImage, enrollments, LocalDateTime.now(), null, new SessionCore(sessionRange, sessionPolicy, sessionStatus));
    }

    public Session(Long id, Course course, CoverImage coverImage, Enrollments enrollments, LocalDateTime createdAt, LocalDateTime updatedAt, SessionCore sessionCore) {
        super(id, createdAt, updatedAt);
        this.course = course;
        this.coverImage = coverImage;
        this.enrollments = enrollments;
        this.sessionCore = sessionCore;
    }

    public void addEnrollment(Enrollment enrollment, Payment payment) {
        sessionCore.validatePaymentAmount(payment);
        sessionCore.validateNotFull(this.enrollments);
        sessionCore.validateSessionStatus();
        this.enrollments.add(enrollment);
    }


    public Course getCourse() {
        return course;
    }

    public CoverImage getCoverImage() {
        return coverImage;
    }

    public SessionCore getSessionCore() {
        return sessionCore;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments.getEnrollments();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(getCourse(), session.getCourse()) && Objects.equals(getCoverImage(), session.getCoverImage()) && Objects.equals(getEnrollments(), session.getEnrollments()) && Objects.equals(getSessionCore(), session.getSessionCore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCourse(), getCoverImage(), getEnrollments(), getSessionCore());
    }

    @Override
    public String toString() {
        return "Session{" +
                "course=" + course +
                ", coverImage=" + coverImage +
                ", enrollments=" + enrollments +
                ", sessionCore=" + sessionCore +
                '}';
    }
}
