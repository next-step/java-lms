package nextstep.images.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.images.domain.Image.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageTest {
    @Test
    @DisplayName("이미지 크기가 1MB를 초과하면 에러 발생")
    void test1() {
        Long id = 1L;
        long size = 1025;
        int width = 300;
        int height = 200;
        ImageType imageType = ImageType.PNG;
        assertThatThrownBy(() -> new Image(id, size, width, height, imageType))
                .hasMessageContaining("이미지 크기는 " + MAX_IMAGE_SIZE + "Byte를 초과할 수 없습니다.");
    }

    @Test
    @DisplayName("가로가 300픽셀 미만이면 에러")
    void test2() {
        Long id = 1L;
        long size = 1024;
        int width = 299;
        int height = 200;
        ImageType imageType = ImageType.PNG;
        assertThatThrownBy(() -> new Image(id, size, width, height, imageType))
                .hasMessageContaining("가로는 최소 " + MIN_WIDTH_SIZE + "이상이어야 합니다.");
    }

    @Test
    @DisplayName("세로가 200픽셀 미만이면 에러")
    void test3() {
        Long id = 1L;
        long size = 1024;
        int width = 300;
        int height = 199;
        ImageType imageType = ImageType.PNG;
        assertThatThrownBy(() -> new Image(id, size, width, height, imageType))
                .hasMessageContaining("세로는 최소 " + MIN_HEIGHT_SIZE + "이상이어야 합니다.");
    }

    @Test
    @DisplayName("가로세로 비율이 3:2가 아니면 에러")
    void test4() {
        Long id = 1L;
        long size = 1024;
        int width = 600;
        int height = 500;
        ImageType imageType = ImageType.PNG;
        assertThatThrownBy(() -> new Image(id, size, width, height, imageType))
                .hasMessageContaining("비율은 가로:세로 " + RATIO + "여야 합니다.");
    }
}