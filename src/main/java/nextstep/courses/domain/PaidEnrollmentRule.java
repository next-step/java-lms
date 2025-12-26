package nextstep.courses.domain;

public class PaidEnrollmentRule implements EnrollmentRule {
    private final Money price;
    private final Capacity capacity;

    public PaidEnrollmentRule(int price, int capacity) {
        this(new Money(price), new Capacity(capacity));
    }

    public PaidEnrollmentRule(Money price, Capacity capacity) {
        this.price = price;
        this.capacity = capacity;
    }

    @Override
    public void validate(Money money, int enrolledCount) {
        validateMoney(money);
        validateCapacity(enrolledCount);
    }

    @Override
    public void validateMoney(Money money) {
        if (!price.matches(money)) {
            throw new IllegalArgumentException("결제 금액이 수강료와 일치하지 않습니다.");
        }
    }

    @Override
    public void validateCapacity(int enrolledCount) {
        if (capacity.isFull(enrolledCount)) {
            throw new IllegalArgumentException("수강 인원이 초과되었습니다.");
        }
    }

    @Override
    public SessionType getType() {
        return SessionType.PAID;
    }

    public int getPrice() {
        return this.price.getMoney();
    }

    public int getCapacity() {
        return this.capacity.getCapacity();
    }
}
