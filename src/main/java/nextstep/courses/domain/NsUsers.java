package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class NsUsers {
    private Long id;
    private List<NsUser> nsUsers = new ArrayList<>();

    public NsUsers() {
    }

    public NsUsers(Long id) {
        this.id = id;
    }

    public void enroll(NsUser user) {
        nsUsers.add(user);
    }

    public int count() {
        return nsUsers.size();
    }

    public Long getId() {
        return id;
    }

    public List<NsUser> getNsUsers() {
        return nsUsers;
    }
}
