package nextstep.courses.domain;

public class Width {
    private final Integer value;

    public Width(Integer width) {
        if (width < 300) {
            throw new IllegalArgumentException("너비는 300px 이상이어야 합니다.");
        }

        this.value = width;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Width width = (Width) o;
        return value.equals(width.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
