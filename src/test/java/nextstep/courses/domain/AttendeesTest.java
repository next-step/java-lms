package nextstep.courses.domain;

import nextstep.courses.exception.AlreadyTakingSessionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.fixture.NsUserFixture.*;
import static org.assertj.core.api.Assertions.*;

class AttendeesTest {

    @DisplayName("이미 수강 중인 강의라면 예외가 발생한다.")
    @Test
    void throw_exception_if_user_already_enrolled_session() {
        Period period = new Period(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        SessionInformation information = new SessionInformation(SessionStatus.RECRUITING, SessionType.FREE, period);
        Session session = new FreeSession(1L, information,null);
        Attendee attendee = new Attendee(JAVAJIGI, session);
        Attendees attendees = new Attendees(List.of(attendee));

        assertThatThrownBy(() -> attendees.contains(attendee))
                .isInstanceOf(AlreadyTakingSessionException.class);
    }

}