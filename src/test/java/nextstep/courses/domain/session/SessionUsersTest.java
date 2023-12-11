package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

class SessionUsersTest {

    private static Period newPeriod() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        return new Period(startDate, endDate);
    }

    @Test
    void 유료_강의_최대_수강_인원_초과했을_경우_에러() {
        Payment payment = new Payment(800_000L);
        SessionFee sessionFee = new SessionFee(800_000L);
        int limitedEnrollment = 0;
        Session session = Session.createPaidSession(null, newPeriod(), limitedEnrollment, sessionFee);
        assertThatThrownBy(() -> Enrollment.enroll(payment, session, null)).isInstanceOf(
            IllegalArgumentException.class).hasMessage("강의 최대 수강 인원을 초과했습니다.");
    }
}
