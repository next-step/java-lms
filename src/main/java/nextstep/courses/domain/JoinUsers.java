package nextstep.courses.domain;

import java.util.List;

public class JoinUsers {

    private final List<JoinUser> joinUsers;

    public JoinUsers(List<JoinUser> joinUsers) {

        this.joinUsers = joinUsers;
    }

    public void add(JoinUser joinUser) {
        this.joinUsers.add(joinUser);
    }
}
