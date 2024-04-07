package nextstep.courses.domain;

import nextstep.courses.domain.ImageCapacity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageCapacityTest {

    @Test
    @DisplayName("이미지 용량 크기는 1MB 이하여야 한다.")
    void check_image_capacity() {
        assertThatThrownBy(() -> {
            new ImageCapacity(1_000_001L);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
