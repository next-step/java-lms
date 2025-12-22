package nextstep.courses.domain;

public class Capacity {

    private final int max;
    private int current;

    public Capacity(int max) {
        this(max, 0);
    }

    public Capacity(int max, int current) {
        this.max = max;
        this.current = current;
    }

    public void validateAvailable() {
        if (current >= max) {
            throw new IllegalStateException();
        }
    }

    public void increase() {
        this.current++;
    }
}
