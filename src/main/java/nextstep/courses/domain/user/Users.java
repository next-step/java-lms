package nextstep.courses.domain.user;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Users {

    private final List<User> users;

    public Users(List<User> users) {
        this.users = users;
    }

    public static Users of(List<User> users) {
        return new Users(users);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Users users1 = (Users) o;
        return Objects.equals(users, users1.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users);
    }

    public void add(User user) {
        users.add(user);
    }

    public boolean isContains(User user) {
        return users.contains(user);
    }

    public long count() {
        return users.size();
    }

    public List<User> users() {
        return Collections.unmodifiableList(users);
    }

}
