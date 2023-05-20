package nextstep.courses.domain;

public enum SessionPayment {
  FREE("무료"),
  PAID("유료");

  private final String paymentType;

  SessionPayment(String paymentType) {
    this.paymentType = paymentType;
  }
}
