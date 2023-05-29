package nextstep.images.domain;

import nextstep.lms.domain.Image;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageTest {
    public static final Image DEFAULT_IMAGE = Image.ofDefault();

    @DisplayName("Image 객체가 잘 생성되는지 확인")
    @Test
    void Image_객체가_정상적으로_생성되는지_확인() {
        assertThat(Image.ofDefault()).isInstanceOf(Image.class);
    }

}
