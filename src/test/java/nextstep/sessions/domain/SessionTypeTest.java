package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTypeTest {

    @DisplayName("무료강의는 수강신청의 제한이 없다")
    @Test
    void FreeSessionIsAlwaysTrue() {
        assertThat(SessionType.FREE.isCapacityExceeded(10, 20)).isTrue();
        assertThat(SessionType.FREE.isCapacityExceeded(10, 10)).isTrue();
        assertThat(SessionType.FREE.isCapacityExceeded(10, 0)).isTrue();
    }

    @DisplayName("유료강의는 현재 수강 신청 인원이 최대 수강 인원을 넘지 않았을 때만 등록이 가능하다")
    @Test
    void PaidSessionHavaEnrollCondition() {
        assertThat(SessionType.PAID.isCapacityExceeded(20, 10)).isFalse();
        assertThat(SessionType.PAID.isCapacityExceeded(10, 10)).isFalse();

        assertThat(SessionType.PAID.isCapacityExceeded(1, 10)).isTrue();
    }

}
