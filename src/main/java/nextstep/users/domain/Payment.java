package nextstep.users.domain;

import java.util.Objects;

public class Payment {

  private Price price;

  public Payment() {
  }

  public Payment(Price price) {
    this.price = price;
  }

  public boolean samePrice(Price price) {
    return Objects.equals(this.price, price);
  }
}
