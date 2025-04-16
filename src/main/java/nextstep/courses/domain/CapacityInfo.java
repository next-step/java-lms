package nextstep.courses.domain;

public class CapacityInfo {
    int currentCount;
    int capacity;

    public CapacityInfo(int currentCount, int capacity) {
        this.currentCount = currentCount;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void increaseCurrentCount(){
        this.currentCount++;
    }
}