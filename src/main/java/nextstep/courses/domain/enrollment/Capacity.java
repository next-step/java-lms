package nextstep.courses.domain.enrollment;

public class Capacity implements Comparable<Capacity> {
    private int value;

    public Capacity(int value) {
        validate(value);
        this.value = value;
    }

    private static void validate(int value) {
        if (value < 1) {
            throw new IllegalArgumentException("수강 최대 인원은 1이상이어야 합니다");
        }
    }

    public void update(int value) {
        validate(value);
        this.value = value;
    }

    @Override
    public int compareTo(Capacity o) {
        return Integer.compare(value, o.value);
    }

    public int value() {
        return value;
    }

    public boolean isFull(int size) {
        return size == value;
    }
}
