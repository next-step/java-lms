package nextstep.session.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

class SessionDateTest {

    @Test
    public void sessionDateTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new SessionDate("2023-12-01 09:00:00", "2023-11-30 09:00:00")).withMessageMatching("시작 날짜가 종료 날짜보다 늦을 수 없습니다.");
        assertThatNoException().isThrownBy(() -> new SessionDate("2023-12-01 09:00:00", "2023-12-30 09:00:00"));
    }
}