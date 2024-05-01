package nextstep.users.domain;

import java.util.ArrayList;
import java.util.List;

public class NsUsers {
    private final List<NsUser> nsUsers;

    public NsUsers() {
        this(new ArrayList<>());
    }

    public NsUsers(List<NsUser> nsUsers) {
        this.nsUsers = nsUsers;
    }

    public void add(NsUser nsUser) {
        nsUsers.add(nsUser);
    }

    public int size() {
        return nsUsers.size();
    }
}
