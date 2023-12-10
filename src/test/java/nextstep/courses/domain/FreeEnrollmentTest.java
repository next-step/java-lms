package nextstep.courses.domain;

import nextstep.courses.domain.attendee.Attendee;
import nextstep.courses.domain.attendee.FreeAttendees;
import nextstep.courses.domain.session.FreeEnrollment;
import nextstep.courses.exception.AlreadyTakingSessionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FreeEnrollmentTest {

    @DisplayName("중복으로 수강신청할 경우 예외가 발생한다.")
    @Test
    void throw_exception_if_duplicated_attendees_enrolled() {
        FreeAttendees freeAttendees = new FreeAttendees(List.of(new Attendee(1L, 1L)));
        FreeEnrollment freeEnrollment = new FreeEnrollment(freeAttendees);

        assertThatThrownBy(() -> freeEnrollment.enroll(1L, 1L, 1L))
                .isInstanceOf(AlreadyTakingSessionException.class);
    }
}