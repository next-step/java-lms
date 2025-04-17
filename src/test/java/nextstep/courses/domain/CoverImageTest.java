package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CoverImageTest {

    @Test
    void 이미지_크기는_1MB_이하() {
        assertThat(new CoverImage("jpg", 1000, 300, 200).getSize()).isEqualTo(1000);
    }

    @Test
    void 이미지_크기_1MB_초과() {
        assertThatThrownBy(() -> {
            new CoverImage("jpg", 1100, 300, 200);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이미지_타입은_peg_입력_에러() {
        assertThatThrownBy(() -> {
            new CoverImage("peg", 300, 300, 200);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이미지_타입은_jpg_타입_성공() {
        assertThat(new CoverImage("jpg", 300, 300, 200).getImageType()).isEqualTo(ImageType.JPG);
    }

    @Test
    void width_300_이상_height_200_이상() {
        assertThat(new CoverImage("jpg", 300, 300, 200)).isNotNull();
    }

    @Test
    void width_300_이하() {
        assertThatThrownBy(() -> new CoverImage("jpg", 300, 299, 200))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void width_200_미만() {
        assertThatThrownBy(() -> new CoverImage("jpg", 300, 300, 199))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 비율이_3대2가_아닐때() {
        assertThatThrownBy(() -> new CoverImage("jpg", 300, 300, 300))
                .isInstanceOf(IllegalArgumentException.class);
    }
}