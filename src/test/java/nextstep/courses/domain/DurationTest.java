package nextstep.courses.domain;

import nextstep.courses.InvalidDurationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DurationTest {

    @Test
    @DisplayName("시작일과 종료일을 포함한 기간을 생성할 수 있다")
    public void session_image() {
        LocalDate start = LocalDate.of(2023, 10, 1);
        LocalDate end = LocalDate.of(2023, 12, 31);
        Duration duration = new Duration(start, end);

        assertThat(duration.start()).isEqualTo(start);
        assertThat(duration.end()).isEqualTo(end);
    }

    @Test
    @DisplayName("시작일이 종료일보다 이후일 경우 에러 발생한다")
    public void image_specification_condition() {
        assertThatExceptionOfType(InvalidDurationException.class)
            .isThrownBy(() -> new Duration(LocalDate.of(2024,1,1), LocalDate.of(2023, 11, 26)))
            .withMessageMatching("종료일이 시작일 이전입니다.");
    }

}
