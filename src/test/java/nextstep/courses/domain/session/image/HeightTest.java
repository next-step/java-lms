package nextstep.courses.domain.session.image;

import static nextstep.courses.domain.session.image.Height.MINIMUM_HEIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HeightTest {

    public static final Height HEIGHT = new Height(MINIMUM_HEIGHT);

    @Test
    @DisplayName("새로운 커버 이미지 높이를 생성한다.")
    void Height() {
        assertThat(new Height(MINIMUM_HEIGHT))
                .isEqualTo(HEIGHT);
    }

    @Test
    @DisplayName("높이가 200px 미만인 경우 예외를 던진다.")
    void HeightOutOfRange_Exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Height(MINIMUM_HEIGHT - 1));
    }
}
