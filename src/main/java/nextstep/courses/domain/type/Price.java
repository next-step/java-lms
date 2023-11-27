package nextstep.courses.domain.type;

import nextstep.courses.exception.NegativePriceException;

import java.math.BigDecimal;

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
}
