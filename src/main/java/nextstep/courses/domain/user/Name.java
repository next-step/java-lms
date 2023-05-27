package nextstep.courses.domain.user;

import java.util.Objects;

public class Name {

    private final Long id;

    private final String name;

    public Name(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Name of(Long id, String name) {
        return new Name(id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name1 = (Name) o;
        return Objects.equals(id, name1.id) && Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
