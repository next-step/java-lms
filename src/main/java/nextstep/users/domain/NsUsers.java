package nextstep.users.domain;

import nextstep.courses.domain.session.user.SessionUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<SessionUser> convertSessionUsers(Long sessionId) {
        if (sessionId == null) {
            return new ArrayList<>();
        }
        return this.users.stream()
                .map(item -> new SessionUser(sessionId, item.getId()))
                .collect(Collectors.toList());
    }
}
