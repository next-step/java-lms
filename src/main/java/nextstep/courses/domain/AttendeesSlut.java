package nextstep.courses.domain;

public class AttendeesSlut {

    public static final AttendeesSlut UNLIMIT = new AttendeesSlut(Integer.MAX_VALUE);

    private int slot;
    private int current;

    public AttendeesSlut(int slot) {
        this.slot = slot;
    }

    public boolean available() {
        return current < slot;
    }

    public void plus() {
        current++;
    }
}
