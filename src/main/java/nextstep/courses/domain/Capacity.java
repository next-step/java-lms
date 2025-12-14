package nextstep.courses.domain;

public class Capacity {
    private final int capacity;

    public Capacity(int capacity) {
        validation(capacity);
        this.capacity = capacity;
    }

    private void validation(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("인원이 1 이상이어야 합니다.");
        }
    }

    public boolean isFull(int enrolledCount) {
        return capacity <= enrolledCount;
    }
}
