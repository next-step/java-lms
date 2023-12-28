package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DurationTest {

    @Test
    @DisplayName("SessionDuration 생성")
    void create() {
        assertThat(new Duration(LocalDateTime.now(), LocalDateTime.now().plusMonths(1L)))
                .isInstanceOf(Duration.class);
    }

    @Test
    @DisplayName("SessionDuration 생성시 강의 시작, 강의 종료 에러 던짐")
    void create_throw_exception_startDate_endDate() {
        assertThatThrownBy(() ->
                new Duration(LocalDateTime.now(),
                        LocalDateTime.now().minusMonths(1L))).isInstanceOf(IllegalArgumentException.class);
    }
}
