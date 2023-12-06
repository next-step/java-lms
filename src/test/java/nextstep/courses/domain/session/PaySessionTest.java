package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static nextstep.courses.domain.session.PayType.*;
import static nextstep.courses.domain.session.Status.*;
import static nextstep.users.domain.fixture.DomainFixture.JAVAJIGI;
import static nextstep.users.domain.fixture.DomainFixture.SANJIGI;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaySessionTest {

    @DisplayName("현재 강의의 상태가 모집중이 아니면 예외를 발생시킨다.")
    @Test
    void validateStatus() {
        // given
        PaySession paySession = createPaySession(PREPARE);
        Payment payment = createPayment(10000L);

        // when & then
        assertThatThrownBy(() -> paySession.enroll(new SessionStudent(paySession, JAVAJIGI), payment)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 강의는 현재 준비중입니다.");
    }

    @DisplayName("결제 금액이 강의 금액과 일치하지 않으면 예외를 발생시킨다.")
    @Test
    void validatePayAmount() {
        // given
        PaySession paySession = createPaySession(RECRUIT);
        Payment payment = createPayment(8000L);

        // when & then
        assertThatThrownBy(() -> paySession.enroll(new SessionStudent(paySession, JAVAJIGI), payment)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("결제 금액이 강의 금액과 일치하지 않습니다. 강의 금액 :: 10,000원");
    }

    @DisplayName("현재 수강인원이 제한 인원을 초과하면 예외를 발생시킨다.")
    @Test
    void validateCapacity() {
        // given
        PaySession paySession = createPaySession(RECRUIT);
        Payment payment = createPayment(10000L);
        SessionStudent sessionStudent1 = new SessionStudent(paySession, JAVAJIGI);
        SessionStudent sessionStudent2 = new SessionStudent(paySession, SANJIGI);

        paySession.enroll(sessionStudent1, payment);
        paySession.enroll(sessionStudent2, payment);

        // when & then
        SessionStudent sessionStudent3 = new SessionStudent(paySession, new NsUser());

        assertThatThrownBy(() -> paySession.enroll(sessionStudent3, payment)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("현재 수강 가능한 모든 인원수가 채워졌습니다.");
    }

    private PaySession createPaySession(Status status) {
        return new PaySession(
            1L,
            PAY,
            status,
            new CoverImage(),
            LocalDate.of(2023, 12, 5),
            LocalDate.now(),
            10000L,
            2);
    }

    private Payment createPayment(Long amount) {
        return new Payment("1", 1L, 1L, amount);
    }
}