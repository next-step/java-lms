package nextstep.courses.domain.value;

public class Capacity {

    private final int value;

    public Capacity(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value < 0) {
            throw new RuntimeException("최대 수강 인원은 0 이상이어야 합니다.");
        }
    }

    public boolean isFull(int count) {
        return value <= count;
    }
}
