package nextstep.users.domain;

import java.math.BigDecimal;

public class Price {

  private final BigDecimal price;

  public Price(BigDecimal price) {
    if (price.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("가격은 음수가 될 수 없습니다.");
    }
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Price price1 = (Price) o;

    return price.equals(price1.price);
  }

  @Override
  public int hashCode() {
    return price.hashCode();
  }
}
