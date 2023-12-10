package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class SessionTest {

    @Test
    void 무료_강의일_경우_수강_신청_인원_제한_없음() {
        LocalDate startDate = LocalDate.of(2024, 1, 31);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        Period period = new Period(startDate, endDate);
        Session session = Session.createFreeSession(null, period);

        assertThat(session.limitedEnrollment()).isZero();
    }

    @Test
    void 유료_강의일_경우_수강_신청_인원_제한_있음() {
        LocalDate startDate = LocalDate.of(2024, 1, 31);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        Period period = new Period(startDate, endDate);
        int limitedEnrollment = 100;
        Session session = Session.createPaidSession(null, period, limitedEnrollment, 0);

        assertThat(session.limitedEnrollment()).isEqualTo(100);
    }
}
