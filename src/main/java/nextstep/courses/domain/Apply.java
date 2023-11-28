package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Apply extends BaseEntity {

    private final Long id;
    private final Session session;
    private final NsUser student;

    public Apply(Session session, NsUser student) {
        this(0L, session, student, LocalDateTime.now(), null);
    }

    public Apply(Long id, Session session, NsUser student, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.session = session;
        this.student = student;
    }

    public Session session() {
        return this.session;
    }

    public NsUser student() {
        return this.student;
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
            "session=" + session +
            ", student=" + student +
            '}';
    }
}
