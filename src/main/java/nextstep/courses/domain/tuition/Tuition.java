package nextstep.courses.domain.tuition;

public class Tuition {

    private Free isFree;
    private Money money;

    public Tuition(boolean isFree, int money) {
        this(new Free(isFree), new Money(money));
    }

    public Tuition(Free isFree, Money money) {
        if (isFree.eqaulTo(true) && !money.equals(new Money(0))) {
            throw new IllegalArgumentException("무료 강의의 수강료는 0원이여야 합니다.");
        }
        this.isFree = isFree;
        this.money = money;
    }

    public boolean isEnough(Money submit) {
        return money.isEqualOrSmaller(submit);
    }

    public boolean canRegisterAsFree() {
        return isFree.eqaulTo(true);
    }
}
