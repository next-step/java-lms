package nextstep.courses.domain.session;

import lombok.Getter;
import nextstep.common.domian.BaseDomain;
import nextstep.courses.domain.session.constraint.SessionConstraint;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Session extends BaseDomain {

    private final SessionConstraint constraint;

    private final SessionDescriptor descriptor;

    public Session() {
        this(null, null);
    }

    public Session(String id) {
        this(id, null, null);
    }

    public Session(SessionConstraint constraint, SessionDescriptor descriptor) {
        this(null, constraint, descriptor);
    }

    public Session(String id, SessionConstraint constraint, SessionDescriptor descriptor) {
        super(id);
        this.descriptor = descriptor;
        this.constraint = constraint;
    }

    public Session(String id, boolean deleted, SessionConstraint constraint, SessionDescriptor descriptor) {
        super(id);
        this.descriptor = descriptor;
        this.constraint = constraint;
        this.deleted = deleted;
    }

    public void delete() {
        this.deleted = true;
        this.updatedAt = LocalDateTime.now();
        this.descriptor.deleteSessionImage();
    }

    public boolean canEnroll(int enrollCount, long amount) {
        return descriptor.canEnroll(constraint, enrollCount, amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Session session = (Session) o;
        return Objects.equals(constraint, session.constraint) &&
            Objects.equals(descriptor, session.descriptor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), constraint, descriptor);
    }
}
