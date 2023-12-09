package nextstep.session.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionDateTest {

    @Test
    void 시작일_종료일_생성_성공() {
        // expect
        new SessionDate(LocalDate.now(), LocalDate.now().plusDays(1));
    }

    @Test
    @DisplayName("생성 / 종료일이 생성일보다 빠름 / IllgegalArgumentException")
    void 시작일_종료일_생성_실패() {
        // expect
        assertThatThrownBy(() -> new SessionDate(LocalDate.now(), LocalDate.now().minusDays(1)))
                .isInstanceOf(IllegalArgumentException.class);
    }


}