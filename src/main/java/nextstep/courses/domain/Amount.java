package nextstep.courses.domain;

import java.math.BigInteger;
import java.util.Objects;

/**
 * 가격을 나타내는 VO
 */
public class Amount {
    private final BigInteger amount;

    private Amount(BigInteger amount) {
        if (amount.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.amount = amount;
    }

    public static Amount of(int amount) {
        return new Amount(BigInteger.valueOf(amount));
    }

    public static Amount of(long amount) {
        return new Amount(BigInteger.valueOf(amount));
    }

    public BigInteger getAmount() {
        return amount;
    }

    // 공짜인지를 바로 반환
    public boolean isZero() {
        return amount.equals(BigInteger.ZERO);
    }

    /**
     * VO이기 떄문에 Equals, Hashcode 구현
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Amount amount1 = (Amount)o;
        return Objects.equals(amount, amount1.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
