package nextstep.courses.domain.enrollment;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import nextstep.courses.domain.session.Period;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;

class EnrollmentTest {

    private static Period newPeriod() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        return new Period(startDate, endDate);
    }

    @ParameterizedTest
    @EnumSource(mode = Mode.EXCLUDE, names = {"ENROLLMENT_OPEN"})
    void 강의_상태가_모집중이_아닐_때_수강신청_시_에러(SessionStatus sessionStatus) {
        Session session = new Session(sessionStatus);

        assertThatThrownBy(() -> new Enrollment(session)).isInstanceOf(
            IllegalArgumentException.class).hasMessage("강의 수강신청은 강의 상태가 모집중일 때만 가능합니다.");
    }

    @Test
    void 유료_강의_결제_금액과_수강료가_일치하지_않으면_에러() {
        Payment payment = new Payment(790_000L);
        int limitedEnrollment = 100;
        Session session = Session.createPaidSession(null, newPeriod(), limitedEnrollment, 800_000L);

        assertThatThrownBy(() -> Enrollment.enrollPaidSession(payment, session)).isInstanceOf(
            IllegalArgumentException.class).hasMessage("결제한 금액과 수강료가 일치하지 않습니다.");
    }

    @Test
    void 유료_강의_최대_수강_인원_초과했을_경우_에러() {
        Payment payment = new Payment(800_000L);
        int limitedEnrollment = 0;
        Session session = Session.createPaidSession(null, newPeriod(), limitedEnrollment, 800_000L);

        assertThatThrownBy(() -> Enrollment.enrollPaidSession(payment, session)).isInstanceOf(
            IllegalArgumentException.class).hasMessage("강의 최대 수강 인원을 초과했습니다.");
    }
}
