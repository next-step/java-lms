package nextstep.courses.domain.attendee;

import nextstep.courses.exception.AlreadyApprovedException;
import nextstep.courses.exception.AlreadyNotApprovedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.attendee.Approval.*;
import static org.assertj.core.api.Assertions.*;

class AttendeeTest {

    @DisplayName("이미 승인된 학생은 다시 승인할 경우 예외가 발생한다.")
    @Test
    void throw_exception_when_already_approved_student() {
        Attendee attendee = new Attendee(1L, 1L, APPROVED);

        assertThatThrownBy(attendee::approve)
                .isInstanceOf(AlreadyApprovedException.class);
    }

    @DisplayName("이미 취소된 학생은 다시 취소할 경우 예외가 발생한다.")
    @Test
    void throw_exception_when_already_canceled_student() {
        Attendee attendee = new Attendee(1L, 1L, NOT_APPROVED);

        assertThatThrownBy(attendee::cancel)
                .isInstanceOf(AlreadyNotApprovedException.class);
    }

}