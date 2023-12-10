package nextstep.courses.domain;

import nextstep.courses.domain.attendee.Attendee;
import nextstep.courses.domain.attendee.FreeAttendees;
import nextstep.courses.exception.AlreadyTakingSessionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.fixture.NsUserFixture.*;
import static org.assertj.core.api.Assertions.*;

class FreeAttendeesTest {

    @DisplayName("이미 수강 중인 강의라면 예외가 발생한다.")
    @Test
    void throw_exception_if_user_already_enrolled_session() {
        Attendee attendee = new Attendee(JAVAJIGI.getId(), 1L);
        FreeAttendees freeAttendees = new FreeAttendees(List.of(attendee));

        assertThatThrownBy(() -> freeAttendees.add(attendee))
                .isInstanceOf(AlreadyTakingSessionException.class);
    }

}