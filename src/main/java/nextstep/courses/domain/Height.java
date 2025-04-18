package nextstep.courses.domain;

public class Height {
    private final Integer value;

    public Height(Integer height) {
        if (height < 200) {
            throw new IllegalArgumentException("높이는 200px 이상이어야 합니다.");
        }

        this.value = height;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Height height = (Height) o;
        return value.equals(height.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
