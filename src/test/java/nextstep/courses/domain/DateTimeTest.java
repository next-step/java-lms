package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class DateTimeTest {

    @Test
    void 시작일이_종료일보다_클경우_예외() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new DateTime(LocalDateTime.now().plusDays(1), LocalDateTime.now()))
                .withMessageContaining("시작일은 종료일 이후일 수 없습니다.");
    }

}
