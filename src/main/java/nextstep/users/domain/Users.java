package nextstep.users.domain;

import java.util.ArrayList;
import java.util.List;

public class Users {

    private final List<NsUser> users = new ArrayList<>();

    public Users() {}

    public int getNumberOfUsers() {
        return users.size();
    }

    public void add(NsUser user) {
        this.users.add(user);
    }

    public boolean hasUserOf(NsUser user) {
        return users.contains(user);
    }

}
