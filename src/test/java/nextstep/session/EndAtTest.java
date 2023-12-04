package nextstep.session;

import nextstep.session.domain.EndAt;
import nextstep.session.domain.StartAt;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EndAtTest {

    @Test
    void 종일_생성_시_빈값으로_생성할_수_없다() {
        assertThatThrownBy(() -> new EndAt(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("생성 데이터는 비어있을 수 없습니다.");
    }

    @Test
    void 종료일은_현재보다_이전일_수_없다() {
        assertThatThrownBy(() -> new EndAt(LocalDateTime.now().minusDays(1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("종료일은 현재보다 이전일 수 없습니다.");
    }
}
