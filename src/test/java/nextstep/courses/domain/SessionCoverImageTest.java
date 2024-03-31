package nextstep.courses.domain;

import static nextstep.courses.domain.SessionCoverImage.ALLOWED_EXT;
import static nextstep.courses.domain.SessionCoverImage.HEIGHT_RATIO;
import static nextstep.courses.domain.SessionCoverImage.MAX_BYTE_SIZE;
import static nextstep.courses.domain.SessionCoverImage.MIN_HEIGHT;
import static nextstep.courses.domain.SessionCoverImage.MIN_WIDTH;
import static nextstep.courses.domain.SessionCoverImage.WIDTH_RATIO;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.InvalidCoverImageException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SessionCoverImageTest {

    public static SessionCoverImage SAMPLE_COVER_IMAGE = new SessionCoverImage(
        MAX_BYTE_SIZE, ALLOWED_EXT.get(0), MIN_WIDTH, MIN_HEIGHT, "sample"
    );

    @Test
    @DisplayName("커버 이미지가 유효하면 성공적으로 생성된다")
    void new_success() {
        assertThatNoException()
            .isThrownBy(() -> new SessionCoverImage(
                MAX_BYTE_SIZE, ALLOWED_EXT.get(0), MIN_WIDTH, MIN_HEIGHT, "sample"
        ));
    }

    @Test
    @DisplayName("허용되지 않은 확장자라면 예외가 발생한다")
    void new_fail_for_not_allowed_ext() {
        assertThatThrownBy(() -> new SessionCoverImage(
                MAX_BYTE_SIZE, "not_allowed_ext", MIN_WIDTH, MIN_HEIGHT, "sample"
            )
        ).isInstanceOf(InvalidCoverImageException.class);
    }

    @Test
    @DisplayName("제한 용량을 초과하면 예외가 발생한다")
    void new_fail_for_exceed_byte_size() {
        assertThatThrownBy(() -> new SessionCoverImage(
                MAX_BYTE_SIZE + 1, ALLOWED_EXT.get(0), MIN_WIDTH, MIN_HEIGHT, "sample"
            )
        ).isInstanceOf(InvalidCoverImageException.class);
    }

    @ParameterizedTest
    @DisplayName("가로 또는 세로 크기가 제한보다 작다면 예외가 발생한다")
    @CsvSource(value = {"299,200", "300,199"}, delimiter = ',')
    void new_fail_for_not_satisfied_width_or_height(int width, int height) {
        assertThatThrownBy(() -> new SessionCoverImage(
                MAX_BYTE_SIZE, ALLOWED_EXT.get(0), width, height, "sample"
            )
        ).isInstanceOf(InvalidCoverImageException.class);
    }

    @Test
    @DisplayName("이미지 비율이 유효하지 않으면 예외가 발생한다")
    void new_fail_for_ratio() {
        int width = (int) (MIN_WIDTH * (WIDTH_RATIO + 1));
        int height = (int) (MIN_HEIGHT * HEIGHT_RATIO);
        assertThatThrownBy(() -> new SessionCoverImage(
                MAX_BYTE_SIZE, ALLOWED_EXT.get(0), width, height, "sample"
            )
        ).isInstanceOf(InvalidCoverImageException.class);
    }
}
