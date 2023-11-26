package nextstep.courses.domain;

import nextstep.courses.exception.InvalidDurationException;
import nextstep.courses.domain.type.SessionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DurationTest {

    @Test
    @DisplayName("시작일과 종료일을 포함한 기간을 생성할 수 있다")
    public void duration() {
        LocalDate start = LocalDate.of(2023, 10, 1);
        LocalDate end = LocalDate.of(2023, 12, 31);
        Duration duration = new Duration(start, end);

        assertThat(duration.start()).isEqualTo(start);
        assertThat(duration.end()).isEqualTo(end);
    }

    @Test
    @DisplayName("기간을 입력하지 않은 경우 에러 발생한다")
    public void null_duration() {
        assertThatExceptionOfType(InvalidDurationException.class)
            .isThrownBy(() -> new Duration(null, LocalDate.of(2023, 11, 26)))
            .withMessageMatching("기간을 입력해야합니다.");
    }

    @Test
    @DisplayName("시작일이 종료일보다 이후일 경우 에러 발생한다")
    public void start_after_end_duration() {
        assertThatExceptionOfType(InvalidDurationException.class)
            .isThrownBy(() -> new Duration(LocalDate.of(2024,1,1), LocalDate.of(2023, 11, 26)))
            .withMessageMatching("종료일이 시작일 이전입니다.");
    }

    @ParameterizedTest
    @MethodSource("sessionStatusByDuration")
    @DisplayName("시작일과 종료일에 따른 강의 상태를 확인할 수 있다")
    public void session_status_by_duration(LocalDate start, LocalDate end, LocalDate today, SessionStatus status) {
        assertThat(new Duration(start, end).sessionStatus(today)).isEqualTo(status);
    }

    private static Stream<Arguments> sessionStatusByDuration() {
        LocalDate start = LocalDate.of(2023, 12, 1);
        LocalDate end = LocalDate.of(2023, 12, 31);

        return Stream.of(
            Arguments.of(start, end, LocalDate.of(2023, 11, 26), SessionStatus.READY),
            Arguments.of(start, end, LocalDate.of(2023, 12, 15), SessionStatus.RECRUITING),
            Arguments.of(start, end, LocalDate.of(2024, 1, 1), SessionStatus.TERMINATE)
        );
    }

}
