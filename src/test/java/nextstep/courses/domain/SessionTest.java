package nextstep.courses.domain;

import nextstep.courses.domain.attendee.FreeAttendees;
import nextstep.courses.domain.session.*;
import nextstep.courses.exception.CanNotApplyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static nextstep.courses.domain.session.SessionStatus.*;
import static org.assertj.core.api.Assertions.*;


class SessionTest {

    @DisplayName("모집 중이 아닌 강의는 수강신청할 수 없다.")
    @Test
    void throw_exception_if_session_is_not_recruiting() {
        Period period = new Period(LocalDate.now(),
                                   LocalDate.now().plusDays(1));
        SessionInformation information = new SessionInformation(PREPARING, period);
        Enrollment freeEnrollment = new FreeEnrollment(new FreeAttendees());
        EnrollmentSession session = new EnrollmentSession(1L, information, freeEnrollment);

        assertThatThrownBy(() -> session.enroll(100L, 1L))
                .isInstanceOf(CanNotApplyException.class);
    }

}