package nextstep.courses.domain;

public class Capacity {

    private int max;
    private int current;

    public Capacity(int max, int current) {
        this.max = max;
        this.current = current;
    }

    public void validateAvailable() {
        if (current > max) {
            throw new IllegalStateException();
        }
    }
}
