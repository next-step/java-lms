package nextstep.courses.domain.image;

import nextstep.courses.OutOfImageCapacityException;
import nextstep.courses.domain.Image.ImageCapacity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ImageCapacityTest {

    @Test
    void 이미지_용량은_1MB_이하() {
        int capacity = 2_000;
        Assertions.assertThatThrownBy(() -> new ImageCapacity(capacity))
            .isInstanceOf(OutOfImageCapacityException.class);
    }

}
