package nextstep.courses.domain.course.session;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionDurationTest {
    private LocalDate localDate;

    @BeforeEach
    void setUp() {
        localDate = LocalDate.of(2023, 12, 5);
    }

    @Test
    @DisplayName("Duration 은 시작 혹은 종료 날짜에 빈 값이 주어지면 예외를 던진다.")
    void newObject_nullAndEmpty_throwsException() {
        assertThatThrownBy(
                () -> new SessionDuration(null, null)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
                () -> new SessionDuration(localDate, null)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
                () -> new SessionDuration(null, localDate)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Duration 은 시작 날짜가 종료날짜보다 늦으면 예외를 던진다.")
    void newObject_startDateIsAfterBeforeDate_throwsException() {
        assertThatThrownBy(
                () -> new SessionDuration(localDate.plusDays(1), localDate)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
