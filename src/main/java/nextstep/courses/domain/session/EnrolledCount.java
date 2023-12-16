package nextstep.courses.domain.session;

public class EnrolledCount {
    private int current = 0;
    private int max;

    public EnrolledCount(int max) {
        this.max = max;
    }

    public void add() {
        current += 1;
    }

    public boolean isMax() {
        if (current == max) {
            return true;
        }
        return false;
    }

    public int getCurrent() {
        return current;
    }
}
