package nextstep.session.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CoverImageTest {

    @Test
    void 커버이미지_확장자() {
        assertThatIllegalArgumentException().isThrownBy(() -> new CoverImage(1L, 1L, "test.aaa", 2048, 300, 200)).withMessageMatching("gif, png, jpg, jpeg 파일만 업로드 가능합니다.");
        assertThatNoException().isThrownBy(() -> new CoverImage(1L, 1L, "test.gif", 2048, 300, 200));
    }

    @Test
    void 커버이미지_파일크기() {
        assertThatIllegalArgumentException().isThrownBy(() -> new CoverImage(1L, 1L, "test.jpg", 1024*2048, 300, 200)).withMessageMatching("1MB 이하의 파일만 업로드 가능합니다.");
        assertThatNoException().isThrownBy(() -> new CoverImage(1L, 2L, "test.gif", 300*200, 300, 200));
    }

    @Test
    void 커버이미지_가로세로() {
        assertThatIllegalArgumentException().isThrownBy(() -> new CoverImage(1L, 1L, "test.jpg", 2048, 200, 300)).withMessageMatching("300 \\* 200 이상의 이미지만 업로드 가능합니다.");
    }

}