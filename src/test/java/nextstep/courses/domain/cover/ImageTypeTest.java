package nextstep.courses.domain.cover;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ImageTypeTest {

    @ParameterizedTest
    @ValueSource(strings = {"gif", "jpg", "jpeg", "png", "svg"})
    void 이미지_타입이_gif_jpg_jpeg_png_svg인_경우_True를_반환한다(String input){
        assertThat(ImageType.findAvailableImagesType(ImageType.JPEG)).isTrue();
    }

    @Test
    void 이미지_타입이_gif_jpg_jpeg_png_svg_가_아닌_경우_예외가_발생한다(){
        assertThat(ImageType.findAvailableImagesType(null)).isFalse();
    }

}
