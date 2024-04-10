package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FreeSessionTest {

    @DisplayName("무료 강의의 가격은 0원이다.")
    @Test
    void create() {
        assertThat(new FreeSession().equalsPrice(new Payment("test", 1L, 1L, 0L)))
                .isTrue();
    }

    @DisplayName("무료 강의는 최대 수강인원 제한이 없다.")
    @ParameterizedTest(name = "{0}명이어도 가능!")
    @ValueSource(ints = {3, 4, 5, 12387})
    void isFull_always_false(int userCount) {
        assertThat(new FreeSession().isFull(userCount))
                .isFalse();
    }
}
