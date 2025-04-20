package nextstep.courses.domain.model;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

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
        this.id = id;
        this.courseId = courseId;
        this.period = new SessionPeriod(startDate, endDate);
        this.image = image;
        this.status = status;
        this.price = price;
        this.students = new Students(capacity);
        this.creatorId = creatorId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
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

    public static Session createFreeSession(LocalDateTime startDate, LocalDateTime endDate, SessionImage image, SessionStatus status) {
        return new Session(null, null, startDate, endDate, image, status, 0L, Integer.MAX_VALUE, null);
    }

    public static Session createFreeSession(Course course, LocalDateTime startDate, LocalDateTime endDate, SessionImage image, SessionStatus status, NsUser creator) {
        Session session = new Session(null, course.getId(), startDate, endDate, image, status, 0L, Integer.MAX_VALUE, creator.getId());
        course.addSession(session);
        return session;
    }

    public static Session createPaidSession(LocalDateTime startDate, LocalDateTime endDate, SessionImage image, SessionStatus status, Long price, int capacity) {
        return new Session(null, null, startDate, endDate, image, status, price, capacity, null);
    }

    public static Session createPaidSession(Course cousrse, LocalDateTime startDate, LocalDateTime endDate, SessionImage image, SessionStatus status, Long price, int capacity, NsUser creator) {
        Session session = new Session(null, cousrse.getId(), startDate, endDate, image, status, price, capacity, creator.getId());
        cousrse.addSession(session);
        return session;
    }

    public Payment enroll(Student student) {
        if (status != SessionStatus.OPEN) {
            throw new IllegalArgumentException("session is not open");
        }

        students.register(student, price);
        return new Payment("0L", id, student.getNsUserId(), price);
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
