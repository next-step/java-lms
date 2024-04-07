package nextstep.courses.domain.session;

public class SessionCapacity {

    private final int maxCapacity;
    private int currentCapacity;

    public SessionCapacity(int maxCapacity) {
        this(maxCapacity, 0);
    }

    public SessionCapacity(int maxCapacity, int currentCapacity) {
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }

    public boolean hasCapacity() {
        return currentCapacity < maxCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }
}
