package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class SessionTest {

    @Test
    void startDateMustBeBeforeEndDate() {
        assertThatThrownBy(() -> new Session(LocalDate.of(2025, 12, 18), LocalDate.of(2025, 11, 3))).isInstanceOf(
                IllegalArgumentException.class).hasMessageContaining("시작일이 종료일보다");
    }

}