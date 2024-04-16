package nextstep.courses.domain.session.image;

import static nextstep.courses.domain.session.image.Width.MINIMUM_WIDTH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WidthTest {

    @Test
    @DisplayName("새로운 커버 이미지 너비를 생성한다.")
    void Width() {
        assertThat(new Width(MINIMUM_WIDTH))
                .isEqualTo(new Width(MINIMUM_WIDTH));
    }

    @Test
    @DisplayName("너비가 300px 미만인 경우 예외를 던진다.")
    void WidthOutOfRange_Exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Width(MINIMUM_WIDTH - 1));
    }
}
