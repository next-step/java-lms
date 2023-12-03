package nextstep.courses.domain;

import nextstep.courses.exception.ExceedAttendeesException;
import nextstep.courses.exception.PaymentAmountNotEqualException;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.fixture.NsUserFixture.JAVAJIGI;
import static org.assertj.core.api.Assertions.*;

class PaidSessionTest {

    @DisplayName("결제 금액과 강의 가격이 다를 경우 예외가 발생한다.")
    @Test
    void if_payment_amount_and_price_are_not_same_then_throw_exception() {
        TotalAttendee total = new TotalAttendee(1);
        Price price = new Price(1000L);
        Period period = new Period(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        SessionInformation information = new SessionInformation(SessionStatus.RECRUITING, SessionType.FREE, period);
        Session session = new PaidSession(1L, information, null, total, price);
        Payment payment = new Payment("1L", session.getId(), JAVAJIGI.getId(), 0L);
        Attendees emptyAttendees = new Attendees();

        assertThatThrownBy(() -> session.enroll(payment, JAVAJIGI, emptyAttendees))
                .isInstanceOf(PaymentAmountNotEqualException.class);
    }

    @DisplayName("강의 인원을 초과했을 때 수강신청을 할 경우 예외가 발생한다.")
    @Test
    void test() {
        TotalAttendee total = new TotalAttendee(1, 1);
        Price price = new Price(1000L);
        Period period = new Period(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        SessionInformation information = new SessionInformation(SessionStatus.RECRUITING, SessionType.FREE, period);
        Session session = new PaidSession(1L, information, null, total, price);
        Payment payment = new Payment("1L", session.getId(), JAVAJIGI.getId(), 1000L);
        Attendees emptyAttendees = new Attendees();

        assertThatThrownBy(() -> session.enroll(payment, JAVAJIGI, emptyAttendees))
                .isInstanceOf(ExceedAttendeesException.class);
    }
}