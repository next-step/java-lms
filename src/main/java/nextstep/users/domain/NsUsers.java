package nextstep.users.domain;

import java.util.List;

public class NsUsers {
    private final List<NsUser> nsUsers;

    public NsUsers(List<NsUser> nsUsers) {
        this.nsUsers = nsUsers;
    }

    public void add(NsUser nsUser) {
        nsUsers.add(nsUser);
    }
}
