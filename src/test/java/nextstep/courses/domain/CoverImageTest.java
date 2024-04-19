package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoverImageTest {

    @Test
    void 이미지사이즈_1MB_이상() {
        int imageSize = 1024000001;
        assertThatThrownBy(() -> {
            new CoverImage(imageSize);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이미지타입_일치() {
        CoverImage coverImage = new CoverImage(ImgaeType.JPG);
        assertThat(coverImage).isEqualTo(new CoverImage(ImgaeType.JPG));
    }

    @Test
    void 이미지비율() {
        assertThatThrownBy(() -> {
            new CoverImage(400, 200);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이미지크기() {
        assertThatThrownBy(() -> {
            new CoverImage(100, 50);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
