package nextstep.courses.domain;

import nextstep.courses.exception.ImageVolumeOverException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class VolumeTest {

    @Test
    @DisplayName("이미지 최대 크기를 초과할 경우 에러 발생한다")
    public void volume_limit() {
        assertThatExceptionOfType(ImageVolumeOverException.class)
            .isThrownBy(() -> new Volume(2.0))
            .withMessageMatching("이미지 최대 크기를 초과했습니다.");
    }

}
