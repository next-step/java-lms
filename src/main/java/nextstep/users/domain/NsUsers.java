package nextstep.users.domain;

import java.util.List;

public class NsUsers {
    private List<NsUser> users;

    private NsUsers(List<NsUser> users) {
        this.users = users;
    }

    public static NsUsers from(List<NsUser> nsUsers) {
        return new NsUsers(nsUsers);
    }

    public boolean isSmallerThanMaxSize(int maxSize) {
        return this.users.size() < maxSize;
    }

    public void add(NsUser nsUser) {
        this.users.add(nsUser);
    }

    public void addAll(List<NsUser> nsUsers) {
        if (nsUsers == null) {
            return;
        }
        this.users.addAll(nsUsers);
    }
}
