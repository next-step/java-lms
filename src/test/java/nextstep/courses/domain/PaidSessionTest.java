package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaidSessionTest {

    @Test
    @DisplayName("유료 강의는 최대 수강 인원 5명을 초과할 경우 예외를 발생한다.")
    void 유료_강의는_최대_수강_인원_5명을_초과할_경우_예외를_발생한다() {
        assertThatThrownBy(() -> new PaidSession(6, 1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 다를 때 예외를 발생한다.")
    void 유료_강의는_수강생이_결제한_금액과_수강료가_다를_경우_예외를_발생한다() {
        assertThatThrownBy(() -> new PaidSession(10001))
                .isInstanceOf(IllegalArgumentException.class);
    }
}