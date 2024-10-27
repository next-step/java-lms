package nextstep.session;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Subscribers {

    private final List<NsUser> subscribeUsers = new ArrayList<>();

    public int subscribeUsersSize() {
        return this.subscribeUsers.size();
    }

    public void addUser(NsUser user) {
        this.subscribeUsers.add(user);
    }

    public List<NsUser> getSubscribeUsers() {
        return subscribeUsers;
    }
}
