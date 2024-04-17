package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class ImageTest {
    @DisplayName("이미지 크기는 1MB 이하여야 한다.")
    @Test
    public void imageSizeLessThan1MB() {
        //given & when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    new ImageSize(1000000);
                }).withMessageMatching("이미지 크기는 1메가 바이트를 넘을 수 없습니다.");
    }

    @DisplayName("이미지의 width는 300픽셀 이상이어야 한다.")
    @Test
    public void imageWidthOver300Pixel() {
        //given
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    new ImageWidth(200);
                }).withMessageMatching("이미지 너비는 300px보다 커야 합니다.");
    }

    @DisplayName("이미지의 height는 200픽셀 이상이어야 한다.")
    @Test
    public void imageHeightOver200Pixel() {
        //given
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    new ImageHeight(100);
                }).withMessageMatching("이미지 높이는 200px보다 커야 합니다.");
    }

    @DisplayName("이미지의 width와 height의 비율은 3:2여야 한다.")
    @Test
    public void widthAndHeightRatioEqual3To2() {
        //given
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    new ImageShape(new ImageWidth(400), new ImageHeight(400));
                }).withMessageMatching("가로 세로 비율은 3:2이어야 합니다.");
    }
}
