package nextstep.users.domain;

import java.util.ArrayList;
import java.util.List;

public class Users {

    private final List<NsUser> users = new ArrayList<>();

    public Users() {}

    public Users(List<NsUser> users) {
        this.users.addAll(users);
    }

    public int getNumberOfUsers() {
        return users.size();
    }

    public void add(NsUser user) {
        this.users.add(user);
    }

}
