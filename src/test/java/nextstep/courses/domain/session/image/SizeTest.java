package nextstep.courses.domain.session.image;

import static nextstep.courses.domain.session.image.Size.MAXIMUM_SIZE;
import static nextstep.courses.domain.session.image.Size.MINIMUM_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SizeTest {

    @Test
    @DisplayName("새로운 이미지 크기를 생성한다.")
    void Size() {
        assertThat(new Size(1000L))
                .isEqualTo(new Size(1000L));
    }

    @ParameterizedTest
    @ValueSource(longs = {MINIMUM_SIZE - 1, MAXIMUM_SIZE + 1})
    @DisplayName("크기(Size)가 0MB 이하이거나 1MB를 초과하는 경우 예외를 던진다.")
    void SizeOutOfRange_Exception(final long sizeOutOfRange) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Size(sizeOutOfRange));
    }
}
