package nextstep.courses.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ImageTest {
    @Test
    @DisplayName("image 용량을 확인한다.")
    void check_image_capacity_valid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Image(1048576 + 1, ImageType.GIF, 300, 200);
        });
    }

    @Test
    @DisplayName("image width 크기를 확인한다.")
    void check_image_width_size_valid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Image(1048576, ImageType.GIF, 200, 200);
        });
    }

    @Test
    @DisplayName("image height 크기를 확인한다.")
    void check_image_height_size_valid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Image(1048576, ImageType.GIF, 300, 100);
        });
    }

    @Test
    @DisplayName("image width height 비율을 확인한다.")
    void check_image_width_height_size_valid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Image(1048576, ImageType.GIF, 500, 300);
        });
    }


}
