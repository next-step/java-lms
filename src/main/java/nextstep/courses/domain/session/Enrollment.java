package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Enrollment {
    private final List<NsUser> users;
    private final SessionCapacity sessionCapacity;

    public Enrollment(List<NsUser> users, SessionCapacity sessionCapacity) {
        this.users = users;
        this.sessionCapacity = sessionCapacity;
    }

    public Enrollment(SessionCapacity sessionCapacity) {
        this(new ArrayList<>(), sessionCapacity);
    }

    public Enrollment(int capacity) {
        this(new ArrayList<>(), new SessionCapacity(capacity));
    }

    public Enrollment(List<NsUser> users) {
        this(users, new SessionCapacity(0));
    }

    public void enroll(NsUser user) {
        sessionCapacity.increase();
        this.users.add(user);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(users);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Enrollment enrollment = (Enrollment) object;
        return Objects.equals(enrollment.users, this.users);
    }
}
