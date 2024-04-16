package nextstep.courses.domain;

public class FreeOrPaid {

    private final Free free;
    private final Money tuition;

    public FreeOrPaid(boolean isFree, int tuition) {
        this(new Free(isFree), new Money(tuition));
    }

    public FreeOrPaid(Free free, Money tuition) {
        this.free = free;
        this.tuition = tuition;
    }

    public static FreeOrPaid free() {
        return new FreeOrPaid(true, 0);
    }

    public boolean isFree() {
        return free.isSame(true);
    }

    public boolean canPay(Money submit) {
        return tuition.equals(submit);
    }
}
