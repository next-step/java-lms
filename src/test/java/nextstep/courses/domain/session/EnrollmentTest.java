package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnrollmentTest {
    @Test
    @DisplayName("가격이 0원 미만이면, 예외가 발생한다.")
    void testTitleIsNull() {
        //given
        final long price = -1;

        //when, then
        assertThatThrownBy(() -> new Enrollment(price))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("price cannot be negative");
    }
}
