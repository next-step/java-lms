package nextstep.courses.domain.session;

import java.util.Objects;

public class Name {

    private final String value;

    public Name(final String value) {
        validateSessionNameIsNotNullOrBlank(value);

        this.value = value;
    }

    private void validateSessionNameIsNotNullOrBlank(final String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("강의 이름은 null이거나 빈 문자열, 공백, 개행일 수 없습니다. 이름: " + value);
        }
    }

    public String value() {
        return this.value;
    }

    @Override
    public boolean equals(final Object otherName) {
        if (this == otherName) {
            return true;
        }

        if (otherName == null || getClass() != otherName.getClass()) {
            return false;
        }

        return Objects.equals(this.value, ((Name)otherName).value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }
}
