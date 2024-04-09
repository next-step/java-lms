package nextstep.images.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

class CoverImageTypeTest {

    @Test
    void 존재하지_않는_이미지_타입인_경우_예외가_발생한다() {
        assertThatIllegalArgumentException().isThrownBy(() -> CoverImageType.from("bmp"))
            .withMessage("지원하지 않는 이미지 타입입니다.");
    }

    @Test
    void 이미지_타입이_대문자인_경우도_허용한다() {
        final CoverImageType coverImageType = CoverImageType.from("JPG");

        assertThat(coverImageType).isEqualTo(CoverImageType.JPG);
    }
}
