package nextstep.courses.domain;

import java.util.*;

import nextstep.users.domain.NsUser;

public class Session {

    private final List<NsUser> joinUsers;

    public Session() {
        this(new ArrayList<>());
    }

    public Session(List<NsUser> joinUsers) {
        this.joinUsers = joinUsers;
    }

    public void join(NsUser joinUser) {
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
