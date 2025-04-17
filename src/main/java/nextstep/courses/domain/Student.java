package nextstep.courses.domain;

public class Student {
    private final Long nsUserId;
    private Long money;

    public Student() {
        this(0L);
    }

    public Student(Long money) {
        this(0L, money);
    }

    public Student(Long nsUserId, Long money) {
        this.nsUserId = nsUserId;
        this.money = money;
    }

    public synchronized void pay(Long price) {
        if (money < price) {
            throw new IllegalArgumentException("not enough money");
        }
        money -= price;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    public long getAmount() {
        return money;
    }
}
