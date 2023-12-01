package nextstep.courses.domain;

import nextstep.courses.domain.type.ApplyStatus;
import nextstep.courses.exception.NotRecruitingSessionException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Session extends BaseEntity {

    private final Long id;
    private final Duration duration;
    private final Images images;
    private SessionStatus status;
    protected final Applies applies;

    public Session(Duration duration, Images images) {
        this(0L, duration, images, duration.sessionStatus(LocalDate.now()), LocalDateTime.now(), null);
    }

    public Session(Duration duration, Images images, LocalDateTime createdAt) {
        this(0L, duration, images, duration.sessionStatus(createdAt.toLocalDate()), createdAt, null);
    }

    public Session(Duration duration, Images images, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(0L, duration, images, duration.sessionStatus(createdAt.toLocalDate()), createdAt, updatedAt);
    }

    public Session(Duration duration, Images images, SessionStatus status) {
        this(0L, duration, images, status, LocalDateTime.now(), null);
    }

    public Session(Duration duration, Images images, SessionStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(0L, duration, images, status, createdAt, updatedAt);
    }

    public Session(Long id, Duration duration, Images images, SessionStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.duration = duration;
        this.images = images;
        this.status = status;
        this.applies = new Applies();
    }

    public abstract void apply(Payment payment, NsUser nsUser, LocalDateTime applyAt);

    protected void validateStatus() {
        if (!this.status.isRecruiting()) {
            throw new NotRecruitingSessionException("모집중인 강의가 아닙니다.");
        }
    }

    protected void addStudent(NsUser nsUser, LocalDateTime applyAt) {
        Apply apply = new Apply(this, nsUser, ApplyStatus.APPLYING, applyAt);
        this.applies.add(apply);
    }

    public boolean equalId(Long id) {
        return this.id == id;
    }

    public void approve(Long studentId) {
        Apply apply = this.applies.of(studentId);
        apply.approve();
    }

    public void refuse(Long studentId) {
        Apply apply = this.applies.of(studentId);
        apply.refuse();
    }

    public Long id() {
        return this.id;
    }

    public Duration duration() {
        return this.duration;
    }

    public SessionStatus status() {
        return this.status;
    }

    public Images images() {
        return this.images;
    }

    public Applies applies() {
        return this.applies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        if (!super.equals(o)) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(duration, session.duration) && Objects.equals(images, session.images) && Objects.equals(status, session.status) && Objects.equals(applies, session.applies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, duration, images, status, applies);
    }

    @Override
    public String toString() {
        return "Session{" +
            "id=" + id +
            ", duration=" + duration +
            ", images=" + images +
            ", status=" + status +
            ", applies=" + applies +
            '}';
    }
}
