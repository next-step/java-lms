package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoverImageTest {

    @Test
    @DisplayName("같은 상태를 갖는 두 커버이미지는 동일한 커버이미지이다.")
    void 커버이미지_생성() {
        assertThat(CoverImage.of(1L, "/resources/images/sample")).isEqualTo(CoverImage.of(1L, "/resources/images/sample"));
    }

}