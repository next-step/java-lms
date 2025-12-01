package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.session.constant.SessionStatus;
import nextstep.courses.domain.session.constant.SessionType;

import java.time.LocalDateTime;
import java.util.Objects;

public class Session extends SessionCore {

    private final Long id;
    private final CoverImage coverImage;
    private final Enrollments enrollments;

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
        super(sessionRange, sessionPolicy, sessionStatus);
        this.id = id;
        this.coverImage = coverImage;
        this.enrollments = new Enrollments();
    }

    public Session(Long id, SessionRange sessionRange, SessionPolicy sessionPolicy, SessionStatus sessionStatus, CoverImage coverImage, Enrollments enrollments) {
        super(sessionRange, sessionPolicy, sessionStatus);
        this.id = id;
        this.coverImage = coverImage;
        this.enrollments = enrollments;
    }

    public void addEnrollment(Enrollment enrollment) {
        validatePaymentAmount(enrollment);
        validateNotFull(this.enrollments);
        validateSessionStatus();
        this.enrollments.add(enrollment);
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
