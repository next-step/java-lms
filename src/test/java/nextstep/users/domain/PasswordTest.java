package nextstep.users.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    void 비밀번호_변경() {
        assertThatThrownBy(() -> new Password("1245555"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 비밀번호 입니다.");
    }

    @Test
    void 비밀번호트변경유효성_테스() {
        Password password = new Password("Car1q2w3e4r5t!");
        password.changePassword("Car1q2w3e4r5t#");

        assertThat(password.getPassword()).isEqualTo("Car1q2w3e4r5t#");
    }

}