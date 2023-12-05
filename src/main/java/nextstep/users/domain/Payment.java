package nextstep.users.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Payment {

  private BigDecimal price;

  public Payment() {
  }

  public Payment(BigDecimal price) {
    this.price = price;
  }

  public boolean samePrice(BigDecimal price) {
    return Objects.equals(this.price, price);
  }
}
