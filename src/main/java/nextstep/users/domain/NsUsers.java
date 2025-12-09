package nextstep.users.domain;

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

    public boolean hasLessThan(int size) {
        return this.users.size() < size;
    }
}
