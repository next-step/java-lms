package nextstep.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class BaseTimeEntityTest {

    @Test
    void 시작일과_종료일_생성_시_빈값으로_생성할_수_없다() {
        Throwable throwable = catchThrowable(() -> new BaseTimeEntity(null, null));
        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시작 날짜와 종료 날짜는 비어있을 수 없습니다.");

    }
}
