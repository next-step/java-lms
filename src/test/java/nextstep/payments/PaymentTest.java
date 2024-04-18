package nextstep.payments;

import nextstep.courses.domain.*;
import nextstep.payments.domain.Payment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.List;

public class PaymentTest {
  @Test
  void 결제정보_생성() {
    new Payment("TEST_PAYMENT", 1L, 1L, 100000L);
  }

  @ParameterizedTest
  @CsvSource({ "1,true", "2,false" })
  void 강의에_대한_결제이력인지_확인(Long input, Boolean result) {
    Payment payment = new Payment("TEST_PAYMENT", input, 1L, 100000L);
    ChargedSession session = new ChargedSession(1L, 1L, LocalDate.now(), LocalDate.now().plusMonths(1L),
            List.of(new SessionImage(1L, 300, 200, "gif", 1024, "TEST", 1L)),
            OpenStatus.OPEN, RecruitStatus.OPEN, 20, 100000L);
    Assertions.assertThat(payment.isPaymentFor(session)).isEqualTo(result);
  }
}
