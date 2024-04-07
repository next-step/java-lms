package nextstep.payments;

import nextstep.courses.domain.ChargedSession;
import nextstep.courses.domain.SessionImage;
import nextstep.courses.domain.SessionStatus;
import nextstep.payments.domain.Payment;
import nextstep.payments.domain.Payments;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class PaymentsTest {
  @Test
  void 객체_생성() {
    new Payments(List.of(new Payment(1L, 1L, 10000L)));
  }

  @Test
  void 결제이력_추가() {
    Payments payments = new Payments();
    Payment payment = new Payment(1L, 1L, 10000L);
    payments.add(payment);
    assertThat(payments.contains(payment)).isTrue();
  }

  @Test
  void 결제이력_목록_추가() {
    Payments payments = new Payments();
    Payment payment1 = new Payment(1L, 1L, 10000L);
    Payment payment2 = new Payment(1L, 2L, 10000L);

    payments.addAll(List.of(payment1, payment2));
    assertThat(payments.contains(payment2)).isTrue();
  }

  @Test
  void 결제이력_포함하는지_확인() {
    Payment payment = new Payment(1L, 1L, 10000L);
    Payments payments = new Payments(List.of(payment));

    assertThat(payments.contains(payment)).isTrue();
  }

  @Test
  void 특정_강의에_대한_결제이력_포함하는지_확인() {
    ChargedSession session = new ChargedSession(1L, 1L, LocalDate.now(), LocalDate.now().plusMonths(1L),
            new SessionImage(1L, 300, 200, "gif", 1024, "TEST"),
            SessionStatus.OPEN, 20, 100000L);
    Payment payment1 = new Payment(1L, 1L, 100000L);
    Payment payment2 = new Payment(1L, 2L, 200000L);

    Payments payments = new Payments(List.of(payment1, payment2));

    payments.containsPaymentFor(session);
  }
}
