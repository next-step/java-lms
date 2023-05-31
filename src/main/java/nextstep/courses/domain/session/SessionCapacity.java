package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SessionCapacity {

    private int maximumCapacity;
    private List<NsUser> nsUserList;

    public SessionCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
        this.nsUserList = new ArrayList<>();
    }

    public void addUser(NsUser user) {
        if (isMaximumCapacity()) {
            throw new IllegalArgumentException("정원수를 초과했다");
        }
        nsUserList.add(user);
    }

    private boolean isMaximumCapacity() {
        return maximumCapacity <= nsUserList.size();
    }

    public int getCurrentUserSize() {
        return nsUserList.size();
    }

    @Override
    public String toString() {
        return "SessionCapacity{" +
                "maximumCapacity=" + maximumCapacity +
                ", nsUserList=" + nsUserList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionCapacity that = (SessionCapacity) o;
        return maximumCapacity == that.maximumCapacity && Objects.equals(nsUserList, that.nsUserList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maximumCapacity, nsUserList);
    }
}
