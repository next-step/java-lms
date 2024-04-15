package nextstep.courses.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ImageTest {
    @Test
    @DisplayName("image 용량은 1048576(1MB) 이하이다.")
    void check_image_capacity_valid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Image(1048576 + 1, ImageType.GIF, 300, 200);
        });
    }

    @Test
    @DisplayName("image width 크기는 300 이상이다.")
    void check_image_width_size_valid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Image(1048576, ImageType.GIF, 300 - 1, 200);
        });
    }

    @Test
    @DisplayName("image height 크기는 200 이상이다.")
    void check_image_height_size_valid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Image(1048576, ImageType.GIF, 300, 200 - 1);
        });
    }

    @Test
    @DisplayName("image width height 비율은 3:2를 유지해야한다.")
    void check_image_width_height_size_valid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Image(1048576, ImageType.GIF, 300*2, 200);
        });
    }


}
