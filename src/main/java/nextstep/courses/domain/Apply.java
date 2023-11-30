package nextstep.courses.domain;

import nextstep.courses.domain.type.ApplyStatus;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Apply extends BaseEntity {

    private final Long id;
    private final Session session;
    private final NsUser student;
    private ApplyStatus status;

    public Apply(Session session, NsUser student) {
        this(0L, session, student, ApplyStatus.APPLYING, LocalDateTime.now(), null);
    }

    public Apply(Session session, NsUser student, LocalDateTime createdAt) {
        this(0L, session, student, ApplyStatus.APPLYING, createdAt, null);
    }

    public Apply(Session session, NsUser student, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(0L, session, student, ApplyStatus.APPLYING, createdAt, updatedAt);
    }

    public Apply(Session session, NsUser student, ApplyStatus status) {
        this(0L, session, student, status, LocalDateTime.now(), null);
    }

    public Apply(Session session, NsUser student, ApplyStatus status, LocalDateTime createdAt) {
        this(0L, session, student, status, createdAt, null);
    }

    public Apply(Session session, NsUser student, ApplyStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(0L, session, student, status, createdAt, updatedAt);
    }

    public Apply(Long id, Session session, NsUser student, ApplyStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.session = session;
        this.student = student;
        this.status = status;
    }

    public void approve() {
        this.status = ApplyStatus.APPROVAL;
    }

    public void refuse() {
        this.status = ApplyStatus.REFUSAL;
    }

    public boolean isApproval() {
        return this.status.equals(ApplyStatus.APPROVAL);
    }

    public Session session() {
        return this.session;
    }

    public NsUser student() {
        return this.student;
    }

    public ApplyStatus status() {
        return this.status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Apply)) return false;
        Apply apply = (Apply) o;
        return Objects.equals(id, apply.id) && Objects.equals(session, apply.session) && Objects.equals(student, apply.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, session, student);
    }

    @Override
    public String toString() {
        return "Apply{" +
            "session=" + session.id() +
            ", student=" + student +
            '}';
    }
}
