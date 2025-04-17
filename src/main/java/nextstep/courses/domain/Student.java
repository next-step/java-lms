package nextstep.courses.domain;

public class Student {
    private int money;

    public Student() {
        this(0);
    }

    public Student(int money) {
        this.money = money;
    }

    public synchronized void pay(int price) {
        if (money < price) {
            throw new IllegalArgumentException("not enough money");
        }
        money -= price;
    }
}
