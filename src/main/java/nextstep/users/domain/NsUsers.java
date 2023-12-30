package nextstep.users.domain;

import java.util.List;
import java.util.stream.Collectors;
import nextstep.courses.domain.NsUserLimit;

public class NsUsers {
    private final List<NsUser> userList;

    public NsUsers(List<NsUser> userList) {
        this.userList = userList;
    }

    public void add(NsUser user) {
        userList.add(user);
    }

    public boolean isFull(NsUserLimit limit) {
        return limit.isFull(userList.size());
    }

    public boolean isGreater(NsUserLimit limit) {
        return limit.isLessThan(userList.size());
    }

    public boolean isNotEmpty() {
        return !userList.isEmpty();
    }

    public List<NsUser> diffWith(NsUsers nsUsers) {
        return nsUsers.userList.stream()
                        .filter(e -> !this.userList.contains(e))
                        .collect(Collectors.toList());
    }

    public void replaceAll(NsUsers nsUsers){
        userList.clear();
        userList.addAll(nsUsers.userList);
    }
}
