package nextstep.images.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

class CoverImageTest {

    @Test
    void 이미지_크기가_1MB를_초과하는_경우_업로드_실패한다() {
        assertThatIllegalArgumentException().isThrownBy(() -> new CoverImage(1.1, 300, 200, "jpg"))
                .withMessage("이미지 크기는 1MB를 넘을 수 없습니다.");
    }

    @Test
    void 이미지의_width는_300픽셀_미만이면_업로드_실패한다() {
        assertThatIllegalArgumentException().isThrownBy(() -> new CoverImage(1.0, 299, 200, "jpg"))
                .withMessage("이미지의 width는 300픽셀 미만일 수 없습니다.");
    }

    @Test
    void 이미지의_height는_200픽셀_미만이면_업로드_실패한다() {
        assertThatIllegalArgumentException().isThrownBy(() -> new CoverImage(1.0, 300, 199, "jpg"))
                .withMessage("이미지의 height는 200픽셀 미만일 수 없습니다.");
    }

    @Test
    void 이미지의_width와_height의_비율은_3대2여야_한다() {
        assertThatIllegalArgumentException().isThrownBy(() -> new CoverImage(1.0, 300, 201, "jpg"));
    }
}
