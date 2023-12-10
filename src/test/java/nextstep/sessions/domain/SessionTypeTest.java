package nextstep.sessions.domain;

import nextstep.images.domain.ImageType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
class SessionTypeTest {
    @Test
    @DisplayName("결제 종류는 유료, 무료뿐이다")
    void test1(){
        String type = "no";
        assertThatThrownBy(() -> SessionType.of(type))
                .hasMessageContaining("허용되지 않은 결제 타입입니다.");
    }
}