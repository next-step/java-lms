package nextstep.courses.domain;

import java.util.HashMap;
import java.util.Map;

public enum SessionPayment {
  FREE("무료"),
  PAID("유료");

  private static final String ILLEGAL_PAYMENT_MESSAGE = "무료 및 유료만 가능합니다.";
  private static final Map<String, SessionPayment> BY_PAYMENT = new HashMap<>();

  static {
    for (SessionPayment sessionPayment : values()) {
      BY_PAYMENT.put(sessionPayment.paymentType, sessionPayment);
    }
  }

  private final String paymentType;

  SessionPayment(String paymentType) {
    this.paymentType = paymentType;
  }

  public static SessionPayment valueOfPaymentType(String paymentType) {
    if (!hasContainPaymentType(paymentType)) {
      throw new IllegalArgumentException(ILLEGAL_PAYMENT_MESSAGE);
    }

    return BY_PAYMENT.get(paymentType);
  }

  private static boolean hasContainPaymentType(String paymentType) {
    return BY_PAYMENT.containsKey(paymentType);
  }

  public String getPaymentType() {
    return paymentType;
  }
}
