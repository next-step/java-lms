package nextstep.courses.domain.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class SessionTypeTest {

    @Test
    @DisplayName("문자열 매개변수로 type")
    void findByTypeStr() {
        Optional<SessionType> sessionType = SessionType.findByTypeStr("P");
        assertThat(sessionType.get()).isEqualTo(SessionType.PAY);
    }

    @Test
    @DisplayName("isPay 호출 시 문자열 매개변수가 'P'인 경우 true 반환")
    void isPay() {
        assertThat(SessionType.isPay("P")).isTrue();
        assertThat(SessionType.isPay("F")).isFalse();
    }

}