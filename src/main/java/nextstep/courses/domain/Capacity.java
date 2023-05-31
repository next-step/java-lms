package nextstep.courses.domain;

public class Capacity {
    private int curCapacity;
    private final int maxCapacity;

    public Capacity(int curCapacity, int maxCapacity) {
        this.curCapacity = curCapacity;
        this.maxCapacity = maxCapacity;
    }

    public int getCurCapacity() {
        return curCapacity;
    }

    public void add() {
        int newCapacity = this.curCapacity + 1;

        if (newCapacity <= maxCapacity) {
            this.curCapacity = newCapacity;
            return;
        }

        throw new IllegalArgumentException("최대 수용 인원을 초과하였습니다.");
    }


}
