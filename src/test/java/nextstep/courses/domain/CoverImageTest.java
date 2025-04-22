package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.courses.domain.coverImage.Size;
import nextstep.courses.domain.coverImage.VolumeExceedException;

public class CoverImageTest {
    @Test
    @DisplayName("이미지 크기는 1MB 이하여야 한다.")
    public void coverImageSizeTest() {
        Assertions.assertThatThrownBy(
            () -> Size.ofKilobytes(1025)
        ).isInstanceOf(VolumeExceedException.class);
    }
}
