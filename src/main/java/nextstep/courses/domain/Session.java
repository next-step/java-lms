package nextstep.courses.domain;

import java.util.*;

public class Session {

    private final JoinUsers joinUsers;

    public Session() {
        this(new ArrayList<>());
    }

    public Session(List<JoinUser> joinUsers) {
        this.joinUsers = new JoinUsers(joinUsers);
    }

    public void join(JoinUser joinUser) {
        joinUsers.add(joinUser);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Session session = (Session) o;
        return Objects.equals(joinUsers, session.joinUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(joinUsers);
    }
}
