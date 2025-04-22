package nextstep.courses.domain.model;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

import static nextstep.courses.domain.model.Timestamped.toLocalDateTime;

public class Session extends BaseEntity {
    private final Long courseId;
    private SessionPeriod period;
    private SessionImage image;
    private SessionStatus status;
    private Long price;
    private final Students students;
    private final Long creatorId;

    private Session(Long id, Long courseId, SessionPeriod period, SessionImage image, SessionStatus status, Long price, int capacity, Long creatorId) {
        this(id, courseId, period, image, status, price, capacity, creatorId, LocalDateTime.now(), LocalDateTime.now());
    }

    public Session(Long id, Long courseId, Date startDate, Date endDate, String imagePath, Blob image, String status, Long price, int capacity, Long creatorId, Timestamp createdAt, Timestamp updatedAt) throws SQLException, IOException {
        this(id, courseId, new SessionPeriod(startDate, endDate), new SessionImage(imagePath, image), SessionStatus.valueOf(status), price, capacity, creatorId, toLocalDateTime(createdAt), toLocalDateTime(updatedAt));
    }

    public Session(Long id, Long courseId, SessionPeriod period, SessionImage image, SessionStatus status, Long price, int capacity, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, courseId, period, image, status, price, new Students(capacity), creatorId, createdAt, updatedAt);
    }

    public Session(Long id, Long courseId, SessionPeriod period, SessionImage image, SessionStatus status, Long price, Students students, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.courseId = courseId;
        this.period = period;
        this.image = image;
        this.status = status;
        this.price = price;
        this.students = students;
        this.creatorId = creatorId;
    }


    public static Session createFreeSession(Course course, LocalDateTime startDate, LocalDateTime endDate, SessionImage image, SessionStatus status, NsUser creator) {
        Session session = new Session(null, course.getId(), new SessionPeriod(startDate, endDate), image, status, 0L, Integer.MAX_VALUE, creator.getId());
        course.addSession(session);
        return session;
    }

    public static Session createPaidSession(Course course, SessionPeriod period, SessionImage image, SessionStatus status, Long price, int capacity, NsUser creator) {
        Session session = new Session(null, course.getId(), period, image, status, price, capacity, creator.getId());
        course.addSession(session);
        return session;
    }

    public Student enroll(NsUser user) {
        if (status != SessionStatus.OPEN) {
            throw new IllegalArgumentException("session is not open");
        }

        return students.register(user, this, price);
    }

    public Payment getPayment(NsUser user) {
        if (!students.include(user)) {
            throw new IllegalArgumentException("student not found");
        }
        return new Payment("0L", id, user.getId(), price);
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
                ", courseId=" + courseId +
                ", period=" + period +
                ", image=" + image +
                ", status=" + status +
                ", price=" + price +
                ", students=" + students +
                ", creatorId=" + creatorId +
                '}';
    }
}
