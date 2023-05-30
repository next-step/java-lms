package nextstep.courses.domain;

import java.util.Objects;

public class Cohort {

    private final Long number;

    public Cohort(Long number) {
        this.number = number;
    }

    public static Cohort of(Long number) {
        return new Cohort(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cohort cohort = (Cohort) o;
        return Objects.equals(number, cohort.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
