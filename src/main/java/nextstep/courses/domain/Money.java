package nextstep.courses.domain;

import java.util.Objects;

public class Money {

    private final long amount;

    public Money(long amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money)) {
            return false;
        }
        Money money = (Money) o;
        return amount == money.amount;
    }
}
