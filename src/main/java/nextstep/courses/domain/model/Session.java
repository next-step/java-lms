package nextstep.courses.domain.model;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Session {
    private Long id;
    private final Long courseId;
    private SessionPeriod period;
    private SessionImage image;
    private SessionStatus status;
    private Long price;
    private final Students students;
    private final Long creatorId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private Session(Long id, Long courseId, LocalDateTime startDate, LocalDateTime endDate, SessionImage image, SessionStatus status, Long price, int capacity, Long creatorId) {
        this(id, courseId, new SessionPeriod(startDate, endDate), image, status, price, capacity, creatorId, LocalDateTime.now(), LocalDateTime.now());
    }

    public Session(Long id, Long courseId, SessionPeriod period, SessionImage image, SessionStatus status, Long price, int capacity, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.courseId = courseId;
        this.period = period;
        this.image = image;
        this.status = status;
        this.price = price;
        this.students = new Students(capacity);
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Session createFreeSession(Course course, LocalDateTime startDate, LocalDateTime endDate, SessionImage image, SessionStatus status, NsUser creator) {
        Session session = new Session(null, course.getId(), startDate, endDate, image, status, 0L, Integer.MAX_VALUE, creator.getId());
        course.addSession(session);
        return session;
    }

    public static Session createPaidSession(Course course, LocalDateTime startDate, LocalDateTime endDate, SessionImage image, SessionStatus status, Long price, int capacity, NsUser creator) {
        Session session = new Session(null, course.getId(), startDate, endDate, image, status, price, capacity, creator.getId());
        course.addSession(session);
        return session;
    }

    public Payment enroll(NsUser user) {
        if (status != SessionStatus.OPEN) {
            throw new IllegalArgumentException("session is not open");
        }

        students.register(user, this, price);
        return new Payment("0L", id, user.getId(), price);
    }


    public Long getId() {
        return id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public SessionPeriod getPeriod() {
        return period;
    }

    public SessionImage getImage() {
        return image;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public Long getPrice() {
        return price;
    }

    public Students getStudents() {
        return students;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", period=" + period +
                ", image=" + image +
                ", status=" + status +
                ", price=" + price +
                ", students=" + students +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
