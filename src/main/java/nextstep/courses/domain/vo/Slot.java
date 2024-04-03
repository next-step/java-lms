package nextstep.courses.domain.vo;

public class Slot {
    private int slot;
    private int current;

    public Slot(int slot) {
        this.slot = slot;
    }

    public boolean available() {
        return current < slot;
    }

    public void plus() {
        current++;
    }
}
