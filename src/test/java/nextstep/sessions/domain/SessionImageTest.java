package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

class SessionImageTest {

    public static final SessionImage IMAGE = new SessionImage(
            "cover.png",
            500_000,
            300,
            200
    );

    @Test
    void createImage_success() {
        SessionImage image = new SessionImage(
                "cover.png",
                500_000,
                300,
                200
        );
        assertThat(image.fileName()).isEqualTo("cover.png");
    }

    @Test
    void whenImageSizeExceeds1MB_thenThrow() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new SessionImage("cover.png", 1_048_577, 300, 200)
        ).withMessageContaining("1MB");
    }

    @Test
    void whenImageWidthIsTooSmall_thenThrow() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new SessionImage("cover.png", 100_000, 299, 200)
        ).withMessageContaining("이미지 크기");
    }

    @Test
    void whenImageHeightIsTooSmall_thenThrow() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new SessionImage("cover.png", 100_000, 300, 199)
        ).withMessageContaining("이미지 크기");
    }

    @Test
    void whenImageRatioIsNot3To2_thenThrow() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new SessionImage("cover.png", 100_000, 310, 200)
        ).withMessageContaining("3:2");
    }

    @Test
    void whenFileNameIsEmpty_thenThrow() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new SessionImage("", 100_000, 300, 200)
        ).withMessageContaining("파일명");
    }
}
