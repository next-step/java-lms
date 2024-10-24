package nextstep.study.example;

import java.util.Objects;

public class Name {
    private String name;

    public Name(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
