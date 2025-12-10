package nextstep.users.domain;

import nextstep.courses.domain.session.Capacity;

import java.util.HashSet;
import java.util.Set;

public class NsUsers {
    private final Set<NsUser> users;

    public NsUsers() {
        this(new HashSet<>());
    }

    public NsUsers(Set<NsUser> users) {
        this.users = users;
    }

    public void addUser(NsUser user) {
        this.users.add(user);
    }

    public boolean isLessThan(Capacity capacity) {
        return capacity.isGreaterThan(this.users.size());
    }
}
