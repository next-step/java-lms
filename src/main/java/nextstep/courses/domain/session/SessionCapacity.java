package nextstep.courses.domain.session;

public class SessionCapacity {

    private final Long id;
    private final Long sessionId;
    private final int maxCapacity;
    private int currentCapacity;

    public SessionCapacity(Long id, Long sessionId, int maxCapacity) {
        this(id, sessionId, maxCapacity, 0);
    }

    public SessionCapacity(Long id, Long sessionId, int maxCapacity, int currentCapacity) {
        this.id = id;
        this.sessionId = sessionId;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }

    public boolean hasCapacity() {
        return currentCapacity < maxCapacity;
    }

    public void increaseCurrentCapacity() {
        currentCapacity++;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }
}
