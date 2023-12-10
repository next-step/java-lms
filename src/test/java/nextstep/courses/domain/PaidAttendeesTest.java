package nextstep.courses.domain;

import nextstep.courses.domain.attendee.Attendee;
import nextstep.courses.domain.attendee.PaidAttendees;
import nextstep.courses.exception.AlreadyTakingSessionException;
import nextstep.courses.exception.ExceedAttendeesException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PaidAttendeesTest {

    @DisplayName("중복된 수강생이 있다면 예외가 발생한다.")
    @Test
    void throw_exception_when_duplicated_attendee_exists() {
        List<Attendee> list = new ArrayList<>() {{
            add(new Attendee(1L, 1L));
        }};
        PaidAttendees attendees = new PaidAttendees(list, 2);
        Attendee newAttendee = new Attendee(1L, 1L);

        assertThatThrownBy(() -> attendees.add(newAttendee))
                .isInstanceOf(AlreadyTakingSessionException.class);
    }

    @DisplayName("제한된 인원을 초과할 경우 예외가 발생한다.")
    @Test
    void throw_exception_when_exceed_max_capacity() {
        List<Attendee> list = new ArrayList<>() {{
            add(new Attendee(1L, 1L));
        }};
        PaidAttendees attendees = new PaidAttendees(list, 1);
        Attendee newAttendee = new Attendee(1L, 2L);

        assertThatThrownBy(() -> attendees.add(newAttendee))
                .isInstanceOf(ExceedAttendeesException.class);
    }
}