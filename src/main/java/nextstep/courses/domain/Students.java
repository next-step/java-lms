package nextstep.courses.domain;

import java.util.Objects;

public class Students {

    private int count;

    public Students() {
    }

    public Students(int count) {
        this.count = count;
    }

    public Students add() {
        this.count++;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students students = (Students) o;
        return count == students.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
