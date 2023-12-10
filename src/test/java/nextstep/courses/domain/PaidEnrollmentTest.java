package nextstep.courses.domain;

import nextstep.courses.domain.attendee.Attendee;
import nextstep.courses.domain.attendee.PaidAttendees;
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
        PaidAttendees attendees = new PaidAttendees(List.of(new Attendee(1L, 1L)), 1);
        Price price = new Price(1000L);
        PaidEnrollment paidEnrollment = new PaidEnrollment(attendees, price);

        assertThatThrownBy(() -> paidEnrollment.enroll(1000L, 2L, 1L))
                .isInstanceOf(ExceedAttendeesException.class);
    }

    @DisplayName("중복으로 수강신청할 경우 예외가 발생한다.")
    @Test
    void throw_exception_if_duplicated_attendees_enrolled() {
        PaidAttendees attendees = new PaidAttendees(List.of(new Attendee(1L, 1L)), 2);
        Price price = new Price(1000L);
        PaidEnrollment paidEnrollment = new PaidEnrollment(attendees, price);

        assertThatThrownBy(() -> paidEnrollment.enroll(1000L, 1L, 1L))
                .isInstanceOf(AlreadyTakingSessionException.class);
    }

    @DisplayName("결제 가격과 강의 가격이 일치하지 않는다면 예외가 발생한다.")
    @Test
    void throw_exception_when_payment_amount_is_not_equal_to_price() {
        PaidAttendees attendees = new PaidAttendees(2);
        Price price = new Price(1000L);
        PaidEnrollment paidEnrollment = new PaidEnrollment(attendees, price);
        Long amount = 999L;

        assertThatThrownBy(() -> paidEnrollment.enroll(amount, 1L, 1L))
                .isInstanceOf(PaymentAmountNotEqualException.class);
    }
}
