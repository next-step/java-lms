package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.fixture.NsUserFixture.*;
import static org.assertj.core.api.Assertions.*;

class FreeSessionTest {

    @DisplayName("수강 중이 아닌 무료 강의는 수강할 수 있다.")
    @Test
    void can_enroll_session_when_user_is_not_registered() {
        Period period = new Period(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        SessionInformation information = new SessionInformation(SessionStatus.RECRUITING, SessionType.FREE, period);
        Session session = new FreeSession(1L, information,null);
        Payment payment = new Payment("1L", session.getId(), JAVAJIGI.getId(), 0L);
        Attendees emptyAttendees = new Attendees();
        Attendee expected = new Attendee(JAVAJIGI, session);

        Attendee actual = session.enroll(payment, JAVAJIGI, emptyAttendees);

        assertThat(actual).isEqualTo(expected);
    }

}