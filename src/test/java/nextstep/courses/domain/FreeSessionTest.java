package nextstep.courses.domain;

import nextstep.courses.domain.attendee.Attendee;
import nextstep.courses.domain.session.*;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.courses.domain.session.SessionStatus.*;
import static nextstep.courses.domain.session.SessionType.*;
import static nextstep.fixture.NsUserFixture.*;
import static org.assertj.core.api.Assertions.*;

class FreeSessionTest {

    @DisplayName("모집 중인 무료 강의는 제한없이 수강할 수 있다.")
    @Test
    void can_enroll_session_when_user_is_not_registered() {
        Period period = new Period(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        SessionInformation information = new SessionInformation(RECRUITING, FREE, period);
        Session session = new FreeSession(1L, information, null);
        Payment payment = new Payment("1L", 1L, JAVAJIGI.getId(), 0L);
        Attendee expected = new Attendee(JAVAJIGI.getId(), 1L);

        Attendee actual = session.enroll(payment, JAVAJIGI);

        assertThat(actual).isEqualTo(expected);
    }
}
