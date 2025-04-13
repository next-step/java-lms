package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.time.LocalDate;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionTest {
  private final Period period = new Period(LocalDate.now(), LocalDate.now().plusDays(10));
  private final Image image = new Image(
      new ImageMeta("cover.png", ImageType.PNG, 500_000),
      new Dimension(300, 200)
  );

  @Test
  @DisplayName("정상적인 수강 신청이 가능하다.")
  void register_success() {
    SessionInformation info = new SessionInformation("Java 강의", period, image);
    EnrollmentManager enrollment = new EnrollmentManager(new PaidPolicy(2, 10000L));
    Session session = new Session(info, enrollment);
    session.updateStatus(SessionStatus.OPEN);

    Payment payment = new Payment("pay1", 1L, 101L, 10000L);

    session.register(payment);

    assertThat(session.currentEnrollment()).isEqualTo(1);
  }

  @Test
  @DisplayName("강의 상태가 모집중이 아니면 수강 신청이 불가하다.")
  void register_fail_not_open() {
    SessionInformation info = new SessionInformation("Spring 강의", period, image);
    EnrollmentManager enrollment = new EnrollmentManager(new FreePolicy());
    Session session = new Session(info, enrollment); // 상태는 기본 READY

    Payment payment = new Payment("pay2", 2L, 102L, 0L);

    assertThatIllegalStateException()
        .isThrownBy(() -> session.register(payment))
        .withMessageContaining("모집중인 강의만");
  }

  @Test
  @DisplayName("결제 금액이 일치하지 않으면 예외 발생")
  void register_fail_wrong_amount() {
    SessionInformation info = new SessionInformation("Spring 고급", period, image);
    EnrollmentManager enrollment = new EnrollmentManager(new PaidPolicy(3, 15000L));
    Session session = new Session(info, enrollment);
    session.updateStatus(SessionStatus.OPEN);

    Payment payment = new Payment("pay3", 3L, 103L, 10000L);

    assertThatIllegalArgumentException()
        .isThrownBy(() -> session.register(payment))
        .withMessageContaining("결제 금액");
  }

  @Test
  @DisplayName("정원을 초과한 경우 예외 발생")
  void register_fail_over_limit() {
    SessionInformation info = new SessionInformation("Kotlin 입문", period, image);
    EnrollmentManager enrollment = new EnrollmentManager(new PaidPolicy(1, 5000L));
    Session session = new Session(info, enrollment);
    session.updateStatus(SessionStatus.OPEN);

    session.register(new Payment("pay4", 4L, 104L, 5000L)); // 첫 수강자

    Payment second = new Payment("pay5", 4L, 105L, 5000L); // 정원 초과

    assertThatIllegalStateException()
        .isThrownBy(() -> session.register(second))
        .withMessageContaining("정원 초과");
  }

}
