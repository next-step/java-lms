package nextstep.courses.dto;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.courses.domain.ImageExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoverImageDTOTest {
    @Test
    @DisplayName("CoverImageDTO 생성")
    void create() {
        assertThat(new CoverImageDTO("pobi.png", ImageExtension.PNG, 500L, 300D, 200D))
                .isInstanceOf(CoverImageDTO.class);
    }

    @Test
    @DisplayName("커버 사진 이름 반환")
    void getName() {
        assertThat(
                new CoverImageDTO("pobi.png", ImageExtension.PNG, 500L, 300D, 200D)
                        .getName())
                .hasToString("pobi.png");
    }

    @Test
    @DisplayName("커버 사진 확장자 반환")
    void getExtension() {
        assertThat(
                new CoverImageDTO("pobi.png", ImageExtension.PNG, 500L, 300D, 200D)
                        .getExtension())
                .isEqualTo(ImageExtension.PNG);
    }

    @Test
    @DisplayName("커버 사진 크기 반환")
    void getByteSize() {
        assertThat(
                new CoverImageDTO("pobi.png", ImageExtension.PNG, 500L, 300D, 200D)
                        .getByteSize())
                .isEqualTo(500L);
    }

    @Test
    @DisplayName("커버 사진 너비 반환")
    void getWidth() {
        assertThat(
                new CoverImageDTO("pobi.png", ImageExtension.PNG, 500L, 300D, 200D)
                        .getWidth())
                .isEqualTo(300D);
    }

    @Test
    @DisplayName("커버 사진 높이 반환")
    void getHeight() {
        assertThat(
                new CoverImageDTO("pobi.png", ImageExtension.PNG, 500L, 300D, 200D)
                        .getHeight())
                .isEqualTo(200D);
    }
}
