package nextstep.courses.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class ImageSizeTest {

    @Test
    void 이미지의_width는_300픽셀_이상이어야_한다() {
        // given
        int width = 200;
        int height = 200;

        // when, then
        assertThatThrownBy(() -> new ImageSize(width, height)).isInstanceOf(
                IllegalArgumentException.class)
            .hasMessageContaining("이미지의 width는 300픽셀 이상이어야 한다.");
    }

    @Test
    void 이미지의_height는_200픽셀_이상이어야_한다() {
        // given
        int width = 300;
        int height = 100;

        // when, then
        assertThatThrownBy(() -> new ImageSize(width, height)).isInstanceOf(
                IllegalArgumentException.class)
            .hasMessageContaining("이미지의 height는 200픽셀 이상이어야 한다.");
    }

    @Test
    void 이미지의_width와_height의_비율은_3_대_2여야_한다() {
        // given
        int width = 300;
        int height = 300;

        // when, then
        assertThatThrownBy(() -> new ImageSize(width, height)).isInstanceOf(
                IllegalArgumentException.class)
            .hasMessageContaining("이미지의 width와 height의 비율은 3:2여야 한다.");
    }

    @Test
    void 이미지_크기는_1MB_이하여야_한다() {
        // given
        int width = 1536;
        int height = 1024;

        // when, then
        assertThatThrownBy(() -> new ImageSize(width, height)).isInstanceOf(
                IllegalArgumentException.class)
            .hasMessageContaining("이미지 크기는 1MB 이하여야 합니다.");
    }

    @Test
    void 이미지_사이즈_생성() {
        // given
        int width = 300;
        int height = 200;

        // when
        ImageSize imageSize = new ImageSize(width, height);

        // then
        assertThat(imageSize.getSize()).isEqualTo(width * height * 3);

    }
}
