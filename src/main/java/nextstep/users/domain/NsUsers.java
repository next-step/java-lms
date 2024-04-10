package nextstep.users.domain;

import java.util.ArrayList;
import java.util.List;

public class NsUsers {

    private final List<NsUser> users;

    public NsUsers() {
        this.users = new ArrayList<>();
    }

    public void add(final NsUser user) {
        users.add(user);
    }

    public boolean hasExceededMaxCapacity(final int maxNumber) {
        return users.size() >= maxNumber;
    }

    public boolean contains(final NsUser user) {
        return users.contains(user);
    }

    public List<NsUser> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "NsUsers{" +
                "users=" + users +
                '}';
    }
}
