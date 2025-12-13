package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ImageFileTest {

    @Test
    void 이미지_파일_생성_성공() {
        ImageFile imageFile = new ImageFile(1024 * 1024, "png", 300, 200);
        Assertions.assertThat(imageFile).isNotNull();
    }

    @Test
    void 크기가_1MB_이상일_때_오류() {
        Assertions.assertThatThrownBy(
                        () -> new ImageFile(1024 * 1024 * 2)
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이미지 파일 크기는 1MB 이하여야 합니다.");
    }

    @Test
    void width_300_미만_오류() {
        Assertions.assertThatThrownBy(
                        () -> new ImageFile(1024 * 1024, "png", 200, 200)
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("width 는 300픽셀 이상이여야합니다.");

    }

    @Test
    void width_와_height_의_비율은_3_2() {
        Assertions.assertThatThrownBy(
                        () -> new ImageFile(1024 * 1024, "png", 400, 200)
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("width 와 height 의 비율은 3:2여야 합니다.");
    }

    @Test
    void 이미지_형식에_맞지_않음() {
        Assertions.assertThatThrownBy(
                        () -> new ImageFile(1024 * 1024, "exl", 300, 200)
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("지원하지 않는 이미지 타입입니다.");
    }
}
