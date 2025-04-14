package nextstep.courses.domain;

public class Capacity {
    public static final Capacity ZERO = new Capacity(0);
    private final int value;

    public Capacity(int value) {
        this.value = value;
        validateInput();
    }

    public boolean isFull(int value) {
        return this.value <= value;
    }

    private void validateInput() {
        if (value < 0) {
            throw new IllegalArgumentException("수강인원이 0명 보다 작을 수 없습니다.");
        }
    }

    public int value() {
        return value;
    }
}
