package nextstep.courses.domain.user;

import java.util.Objects;

public class User {

    private final Long id;

    private final String name;

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static User of(long id, String name) {
        return new User(id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
