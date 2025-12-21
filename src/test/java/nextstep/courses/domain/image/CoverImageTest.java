package nextstep.courses.domain.image;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class CoverImageTest {

    @Test
    void 커버_이미지_생성_시_이미지_파일_규칙을_위반하면_예외가_발생한다() {
        assertThatThrownBy(() -> new CoverImage("파일이름.png", 1024 * 1025, 300, 200))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void 커버_이미지_생성_시_이미지_치수_규칙을_위반하면_예외가_발생한다() {
        assertThatThrownBy(() -> new CoverImage("파일이름.png", 1024, 700, 500))
                .isInstanceOf(RuntimeException.class);
    }
}
