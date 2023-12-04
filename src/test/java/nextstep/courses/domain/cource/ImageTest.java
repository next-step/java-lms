package nextstep.courses.domain.cource;

import nextstep.courses.exception.image.ImageFileSizeExceededException;
import nextstep.courses.exception.image.ImageSizeBelowMinException;
import nextstep.courses.exception.image.UnsupportedImageExtensionException;
import nextstep.courses.exception.image.UnsupportedImageRatioException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ImageTest {
    @ParameterizedTest()
    @DisplayName("이미지의 종류는 gif, jpg, jpeg, png, svg만 허용한다.")
    @ValueSource(strings = {"bmp", "exe", "xls"})
    public void type_error(String input){
        Assertions.assertThatThrownBy(() -> Image.of(input, 300L, 200L, 1024)).isInstanceOf(UnsupportedImageExtensionException.class);
    }

    @Test
    @DisplayName("이미지의 용량은 1024KB 이하이여야 한다.")
    public void fileSize_check(){
        Assertions.assertThatThrownBy(() -> Image.of("jpg", 300L, 200L, 1025)).isInstanceOf(ImageFileSizeExceededException.class);
    }
}
