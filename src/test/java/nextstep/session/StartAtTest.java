package nextstep.session;

import nextstep.session.domain.StartAt;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StartAtTest {

    @Test
    void 시작일_생성_시_빈값으로_생성할_수_없다() {
        assertThatThrownBy(() -> new StartAt(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("생성 데이터는 비어있을 수 없습니다.");
    }

    @Test
    void 시작일은_현재보다_이전일_수_없다() {
        assertThatThrownBy(() -> new StartAt(LocalDateTime.now().minusDays(1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시작일은 현재보다 이전일 수 없습니다.");
    }
}
