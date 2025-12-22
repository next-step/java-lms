package nextstep.sessions.domain.image;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

class ImageDimensionTest {

    @Test
    void whenImageWidthIsTooSmall_thenThrow() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new ImageDimension(299, 200)
        ).withMessageContaining("이미지 크기");
    }

    @Test
    void whenImageHeightIsTooSmall_thenThrow() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new ImageDimension(300, 199)
        ).withMessageContaining("이미지 크기");
    }

    @Test
    void whenImageRatioIsNot3To2_thenThrow() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new ImageDimension(310, 200)
        ).withMessageContaining("3:2");
    }

}