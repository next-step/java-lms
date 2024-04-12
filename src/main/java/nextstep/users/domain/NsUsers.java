package nextstep.users.domain;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class NsUsers {
    private final List<NsUser> users = new ArrayList<>();

    public NsUsers() {
    }

    public NsUsers(List<NsUser> users) {
        if (users != null && !users.isEmpty()) {
            this.users.addAll(users);
        }
    }

    public void add(NsUser user) {
        Assert.notNull(user, "추가할 사용자는 필수값입니다.");

        this.users.add(user);
    }

    public int size() {
        return users.size();
    }


    public boolean contains(NsUser requestUser) {
        return users.stream()
                .anyMatch(user -> user.matchUser(requestUser));
    }
}
