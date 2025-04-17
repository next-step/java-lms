package nextstep.courses.domain;

public class Size {
    private final Long value;

    public Size(Long value) {
        if (value > 1_048_576) {
            throw new IllegalArgumentException("커버 이미지의 크기는 1MB 이하여야 합니다.");
        }

        this.value = value;
    }
}
