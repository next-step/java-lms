package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class SessionTest {

    @DisplayName("시작일, 종료일, 이미지, 가격, 상태를 전달하면 강의 객체를 생성한다.")
    @Test
    void sessionTest() {
        assertThat(new Session(LocalDate.of(2023, 11, 1),
                LocalDate.of(2023, 11, 30),
                SessionImageTest.IMAGE_JPG,
                SessionChargeTest.FREE,
                SessionStatus.END)).isInstanceOf(Session.class);
        assertThat(new Session(LocalDate.of(2023, 12, 1),
                LocalDate.of(2023, 12, 31),
                SessionImageTest.IMAGE_PNG,
                SessionChargeTest.CHARGE_1000,
                SessionStatus.RECRUITING)).isInstanceOf(Session.class);
    }
}
