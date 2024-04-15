package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ImageCoverTest {
    @DisplayName("실패 - 이미지 크기 초과 테스트")
    @Test
    void image_size_fail_test() {
        assertThatIllegalArgumentException().isThrownBy((() ->
                new ImageCover(5, 300, 200, "jpg")));
    }
}
