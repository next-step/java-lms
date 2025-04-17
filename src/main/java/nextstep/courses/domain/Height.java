package nextstep.courses.domain;

public class Height {
    private final Double value;

    public Height(Double height) {
        if (height < 200.0) {
            throw new IllegalArgumentException("높이는 200px 이상이어야 합니다.");
        }

        this.value = height;
    }
}
