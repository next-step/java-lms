package nextstep.courses.domain;

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
        Assertions.assertThatThrownBy(() -> {
            Image.of(input, 300L, 200L, 1024);
        }).isInstanceOf(UnsupportedImageExtensionException.class);
    }

    @Test
    @DisplayName("이미지의 용량은 1024KB 이하이여야 한다.")
    public void fileSize_check(){
        Assertions.assertThatThrownBy(() -> {
            Image.of("jpg", 300L, 200L, 1025);
        }).isInstanceOf(ImageFileSizeExceededException.class);
    }

    @Test
    @DisplayName("이미지 크기는 width 300, height 200 이상이어야 하며, 비율은 3:2 이여야 한다.")
    public void check_imageSize_and_ratio(){
        Assertions.assertThatThrownBy(() -> {
            Image.of("jpg", 299L, 200L, 1024);
        }).isInstanceOf(ImageSizeBelowMinException.class);

        Assertions.assertThatThrownBy(() -> {
            Image.of("jpg", 299L, 199L, 1024);
        }).isInstanceOf(ImageSizeBelowMinException.class);

        Assertions.assertThatThrownBy(() -> {
            Image.of("jpg", 300L, 201L, 1024);
        }).isInstanceOf(UnsupportedImageRatioException.class);
    }
}
