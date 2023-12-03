package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class SessionTypeTest {

    @Test
    public void 무료_강의_반환_테스트() {
        SessionType sessionType = SessionType.determineSessionType(false, null, null);
        assertThat(sessionType).isExactlyInstanceOf(FreeSession.class);
    }

    @Test
    public void 유료_강의_반환_테스트() {
        SessionType sessionType = SessionType.determineSessionType(true, 100, 10000);
        assertThat(sessionType).isExactlyInstanceOf(PaidSession.class);
    }

    @Test
    public void 유료_강의_수강인원_에러_테스트() {
        assertThatThrownBy(() -> SessionType.determineSessionType(true, -1, 10000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 유료_강의_수강료_에러_테스트() {
        assertThatThrownBy(() -> SessionType.determineSessionType(true, 0, -1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
