package nextstep.courses.domain;

import nextstep.courses.domain.attendee.Attendee;
import nextstep.courses.domain.attendee.Attendees;
import nextstep.courses.domain.session.PaidEnrollment;
import nextstep.courses.domain.session.Price;
import nextstep.courses.exception.AlreadyTakingSessionException;
import nextstep.courses.exception.ExceedAttendeesException;
import nextstep.courses.exception.PaymentAmountNotEqualException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PaidEnrollmentTest {

    @DisplayName("제한된 인원을 넘을 경우 예외가 발생한다.")
    @Test
    void throw_exception_when_exceed_limited_capacity() {
        Attendees attendees = new Attendees(List.of(new Attendee(1L, 1L)));
        Price price = new Price(1000L);
        PaidEnrollment paidEnrollment = new PaidEnrollment(attendees, price, 1);

        assertThatThrownBy(() -> paidEnrollment.enroll(1L, 2L, 1L))
                .isInstanceOf(ExceedAttendeesException.class);
    }

    @DisplayName("중복으로 수강신청할 경우 예외가 발생한다.")
    @Test
    void throw_exception_if_duplicated_attendees_enrolled() {
        Attendees attendees = new Attendees(List.of(new Attendee(1L, 1L)));
        Price price = new Price(1000L);
        PaidEnrollment paidEnrollment = new PaidEnrollment(attendees, price, 2);

        assertThatThrownBy(() -> paidEnrollment.enroll(1L, 1L, 1L))
                .isInstanceOf(AlreadyTakingSessionException.class);
    }

    @DisplayName("결제 가격과 강의 가격이 일치하지 않는다면 예외가 발생한다.")
    @Test
    void throw_exception_when_payment_amount_is_not_equal_to_price() {
        Attendees attendees = new Attendees();
        Price price = new Price(1000L);
        PaidEnrollment paidEnrollment = new PaidEnrollment(attendees, price, 2);
        Long amount = 999L;

        assertThatThrownBy(() -> paidEnrollment.enroll(amount, 1L, 1L))
                .isInstanceOf(PaymentAmountNotEqualException.class);
    }
}