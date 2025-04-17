package nextstep.courses.domain;

public class Width {
    private final Double value;

    public Width(Double width) {
        if (width < 300) {
            throw new IllegalArgumentException("너비는 300px 이상이어야 합니다.");
        }

        this.value = width;
    }
}
