package nextstep.courses.domain;

import java.util.List;
import java.util.Objects;

public class JoinUsers {

    private final List<JoinUser> joinUsers;

    public JoinUsers(List<JoinUser> joinUsers) {

        this.joinUsers = joinUsers;
    }

    public void add(JoinUser joinUser) {
        this.joinUsers.add(joinUser);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JoinUsers joinUsers1 = (JoinUsers) o;
        return Objects.equals(joinUsers, joinUsers1.joinUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(joinUsers);
    }
}
