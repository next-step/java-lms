package nextstep.courses.domain;

import nextstep.courses.InvalidCoverImageException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CoverImageTest {

    @Test
    void 유효한_커버이미지는_예외없이_생성되고_검증_통과() {
        assertThatCode(() ->
                new CoverImage(600, 400, 500_000, ImageExtension.JPG)
        ).doesNotThrowAnyException();
    }

    @Test
    void 크기가_1MB_초과하면_예외발생() {
        assertThatThrownBy(() ->
                new CoverImage(600, 400, 2_000_000, ImageExtension.PNG)
        ).isInstanceOf(InvalidCoverImageException.class);
    }

    @Test
    void 너비가_300미만이면_예외발생() {
        assertThatThrownBy(() ->
                new CoverImage(299, 400, 500_000, ImageExtension.PNG)
        ).isInstanceOf(InvalidCoverImageException.class);
    }

    @Test
    void 높이가_200미만이면_예외발생() {
        assertThatThrownBy(() ->
                new CoverImage(600, 199, 500_000, ImageExtension.PNG)
        ).isInstanceOf(InvalidCoverImageException.class);
    }

    @Test
    void 비율이_3대2_아니면_예외발생() {
        assertThatThrownBy(() ->
                new CoverImage(600, 500, 500_000, ImageExtension.PNG)
        ).isInstanceOf(InvalidCoverImageException.class);
    }

    @Test
    void 지원하지_않는_확장자면_예외발생() {
        assertThatThrownBy(() ->
                new CoverImage(600, 400, 500_000, null)
        ).isInstanceOf(InvalidCoverImageException.class);
    }
}
