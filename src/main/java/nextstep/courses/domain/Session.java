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
    private final Images images;
    private SessionStatus status;
    protected final List<Apply> applys = new ArrayList<>();

    public Session(Duration duration, Images images) {
        this(0L, duration, images, duration.sessionStatus(LocalDate.now()), LocalDateTime.now(), null);
    }

    public Session(Duration duration, Images images, SessionStatus status) {
        this(0L, duration, images, status, LocalDateTime.now(), null);
    }

    public Session(Long id, Duration duration, Images images, SessionStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.duration = duration;
        this.images = images;
        this.status = status;
    }

    public abstract void apply(Payment payment, NsUser nsUser);

    protected void validateStatus() {
        if (!this.status.equals(SessionStatus.RECRUITING)) {
            throw new NotRecruitingSessionException("모집중인 강의가 아닙니다.");
        }
    }

    protected void addStudent(NsUser nsUser) {
        this.applys.add(new Apply(this, nsUser));
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

    public List<Apply> applys() {
        return Collections.unmodifiableList(this.applys);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(duration, session.duration) && Objects.equals(images, session.images) && status == session.status && Objects.equals(applys, session.applys);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, duration, images, status, applys);
    }

    @Override
    public String toString() {
        return "Session{" +
            "id=" + id +
            ", duration=" + duration +
            ", images=" + images +
            ", status=" + status +
            ", applys=" + applys +
            '}';
    }
}
