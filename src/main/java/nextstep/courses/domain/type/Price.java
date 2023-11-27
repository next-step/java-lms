package nextstep.courses.domain.type;

import nextstep.courses.exception.NegativePriceException;

import java.math.BigDecimal;
import java.util.Objects;

public class Price {

    private final BigDecimal price;

    public Price(BigDecimal price) {
        this.price = price;
        validate();
    }

    private void validate() {
        if (this.price.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativePriceException("금액은 음수일 수 없습니다.");
        }
    }

    public boolean isEqualAmount(BigDecimal price) {
        if (this.price.compareTo(price) == 0) {
            return true;
        }
        return false;
    }

    public BigDecimal price() {
        return this.price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price1 = (Price) o;
        return Objects.equals(price, price1.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }

    @Override
    public String toString() {
        return "Price{" +
            "price=" + price +
            '}';
    }
}
