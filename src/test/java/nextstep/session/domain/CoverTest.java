package nextstep.session.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoverTest {

    @DisplayName("이미지 크기는 1MB 이하여야 한다.")
    @Test
    void throwIllegalArgumentExceptionIfImageSizeOverThan1MB() {
        // given
        Resolution resolution = new Resolution(300, 200);
        ImageFilePath imageFilePath = new ImageFilePath("/home", "temp", "jpg");

        // then
        assertThatThrownBy(() -> new Cover(resolution, imageFilePath, 10000000))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
