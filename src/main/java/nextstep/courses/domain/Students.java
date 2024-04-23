package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Students {

    private final List<NsUser> users;

    public Students() {
        users = new ArrayList<>();
    }

    public Students(List<NsUser> users) {
        this.users = users;
    }

    public Capability count() {
        return new Capability(users.size());
    }

    public void add(NsUser user) {
        this.users.add(user);
    }

    public boolean contains(NsUser user) {
        return this.users.contains(user);
    }
}
