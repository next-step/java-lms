package nextstep.courses.domain;

import nextstep.courses.domain.session.TotalAttendee;
import nextstep.courses.exception.ExceedAttendeesException;
import nextstep.courses.exception.NegativeNumberException;
import nextstep.courses.exception.NegativeOrZeroNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class TotalAttendeeTest {

    @DisplayName("모집 인원을 초과할 경우 예외가 발생한다.")
    @Test
    void throw_exception_when_exceed_recruiting_number() {
        TotalAttendee totalAttendee = new TotalAttendee(1, 1);

        assertThatThrownBy(totalAttendee::add)
                .isInstanceOf(ExceedAttendeesException.class);
    }

    @DisplayName("모집 인원은 0 혹은 음수일 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void throw_exception_when_recruiting_number_is_zero_or_negative(int given) {
        assertThatThrownBy(() -> new TotalAttendee(given))
                .isInstanceOf(NegativeOrZeroNumberException.class);
    }

    @DisplayName("수강 인원은 음수일 수 없다.")
    @Test
    void throw_exception_when_attendees_number_is_negative() {
        assertThatThrownBy(() -> new TotalAttendee(1, -1))
                .isInstanceOf(NegativeNumberException.class);
    }

    @DisplayName("수강 인원은 추가할 때 1씩 증가한다.")
    @Test
    void plus_one_attendee_when_add() {
        TotalAttendee totalAttendee = new TotalAttendee(1);
        TotalAttendee expected = new TotalAttendee(1, 1);

        TotalAttendee actual = totalAttendee.add();

        assertThat(actual).isEqualTo(expected);
    }
}