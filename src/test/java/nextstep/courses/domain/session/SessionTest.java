package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class SessionTest {

    private static Period newPeriod() {
        LocalDate startDate = LocalDate.of(2024, 1, 31);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        return new Period(startDate, endDate);
    }

    @Test
    void 무료_강의일_경우_수강_신청_인원_제한_없음() {
        Session session = Session.createFreeSession(null, newPeriod());
        assertThat(session.limitedEnrollment()).isZero();
    }

    @Test
    void 유료_강의일_경우_수강_신청_인원_제한_있음() {
        SessionFee sessionFee = new SessionFee(800_000L);
        int limitedEnrollment = 100;
        Session session = Session.createPaidSession(null, newPeriod(), 100, sessionFee);
        assertThat(session.limitedEnrollment()).isEqualTo(100);
    }
}
