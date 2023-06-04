package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void 이메일형식_체크_1() {
        assertThatThrownBy(() -> {
            new Email("testsettest@asdfasgegasdgege");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 이메일 형식이 아닙니다.");
    }

    @Test
    void 이메일형식_체크_2() {
        assertThat(new Email("test@gmail.com").getEmail()).isEqualTo("test@gmail.com");
    }
}