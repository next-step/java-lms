package nextstep.courses.domain;

import nextstep.courses.domain.type.SessionStatus;
import nextstep.courses.exception.NotRecruitingSessionException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Session extends BaseEntity {

    private final Long id;
    private final Duration duration;
    private final Image image;
    private SessionStatus status;
    protected final List<NsUser> students = new ArrayList<>();

    public Session(Duration duration, Image image) {
        this(0L, duration, image, duration.sessionStatus(LocalDate.now()), LocalDateTime.now(), null);
    }

    public Session(Duration duration, Image image, SessionStatus status) {
        this(0L, duration, image, status, LocalDateTime.now(), null);
    }

    public Session(Long id, Duration duration, Image image, SessionStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.duration = duration;
        this.image = image;
        this.status = status;
    }

    public abstract void apply(Payment payment, NsUser nsUser);

    protected void validateStatus() {
        if (!this.status.equals(SessionStatus.RECRUITING)) {
            throw new NotRecruitingSessionException("모집중인 강의가 아닙니다.");
        }
    }

    protected void addStudent(NsUser nsUser) {
        this.students.add(nsUser);
    }

    public List<NsUser> students() {
        return Collections.unmodifiableList(this.students);
    }

    public Duration duration() {
        return this.duration;
    }

    public SessionStatus status() {
        return this.status;
    }

    public Image image() {
        return this.image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(duration, session.duration) && Objects.equals(image, session.image) && status == session.status && Objects.equals(students, session.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, duration, image, status, students);
    }

    @Override
    public String toString() {
        return "Session{" +
            "id=" + id +
            ", duration=" + duration +
            ", image=" + image +
            ", status=" + status +
            ", students=" + students +
            '}';
    }

}
